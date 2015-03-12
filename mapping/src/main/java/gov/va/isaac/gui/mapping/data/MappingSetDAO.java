package gov.va.isaac.gui.mapping.data;

import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.constants.ISAAC;
import gov.va.isaac.constants.MappingConstants;
import gov.va.isaac.util.OTFUtility;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.ihtsdo.otf.query.lucene.LuceneDynamicRefexIndexerConfiguration;
import org.ihtsdo.otf.tcc.api.blueprint.DescriptionCAB;
import org.ihtsdo.otf.tcc.api.blueprint.IdDirective;
import org.ihtsdo.otf.tcc.api.blueprint.InvalidCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexDynamicCAB;
import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.description.DescriptionVersionBI;
import org.ihtsdo.otf.tcc.api.lang.LanguageCode;
import org.ihtsdo.otf.tcc.api.metadata.ComponentType;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;
import org.ihtsdo.otf.tcc.api.refexDynamic.RefexDynamicChronicleBI;
import org.ihtsdo.otf.tcc.api.refexDynamic.RefexDynamicVersionBI;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicColumnInfo;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicDataBI;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicDataType;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicUsageDescription;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicValidatorType;
import org.ihtsdo.otf.tcc.model.cc.refexDynamic.data.RefexDynamicUsageDescriptionBuilder;
import org.ihtsdo.otf.tcc.model.cc.refexDynamic.data.dataTypes.RefexDynamicString;
import org.ihtsdo.otf.tcc.model.cc.refexDynamic.data.dataTypes.RefexDynamicUUID;

/**
 * {@link MappingSet}
 *
 * A Convenience class to hide unnecessary OTF bits from the Mapping APIs.
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
public class MappingSetDAO
{
	public static RefexDynamicVersionBI<?> createmappingSet(MappingSet mappingSet) throws IOException
	{
		ConceptVersionBI mappingConcept_;
		RefexDynamicVersionBI<?> mappingRefexData_;
		
		try
		{
			//We need to create a new concept - which itself is defining a dynamic refex - so set that up here.
			RefexDynamicUsageDescription rdud = RefexDynamicUsageDescriptionBuilder.createNewRefexDynamicUsageDescriptionConcept(mappingSet.getName(), mappingSet.getName(), mappingSet.getDescription(), 
					new RefexDynamicColumnInfo[] {
						new RefexDynamicColumnInfo(0, ISAAC.REFEX_COLUMN_TARGET_COMPONENT.getPrimodialUuid(), RefexDynamicDataType.UUID, null, false, null, null),
						new RefexDynamicColumnInfo(1, MappingConstants.MAPPING_QUALIFIERS.getPrimodialUuid(), RefexDynamicDataType.UUID, null, false, 
								RefexDynamicValidatorType.IS_KIND_OF, new RefexDynamicUUID(MappingConstants.MAPPING_QUALIFIERS.getPrimodialUuid())),
						new RefexDynamicColumnInfo(2, MappingConstants.MAPPING_STATUS.getPrimodialUuid(), RefexDynamicDataType.UUID, null, false, 
								RefexDynamicValidatorType.IS_KIND_OF, new RefexDynamicUUID(MappingConstants.MAPPING_STATUS.getPrimodialUuid()))}, 
					null, true, ComponentType.CONCEPT);
			
			//TODO background thread this
			LuceneDynamicRefexIndexerConfiguration.configureColumnsToIndex(rdud.getRefexUsageDescriptorNid(), new Integer[] {0, 1, 2}, true);
			
			//Then, annotate the concept created above as a member of the MappingSet dynamic refex, and add the inverse name, if present.
			ConceptVersionBI createdConcept = OTFUtility.getConceptVersion(rdud.getRefexUsageDescriptorNid());
			if (!StringUtils.isBlank(mappingSet.getInverseName()))
			{
				DescriptionCAB dCab = new DescriptionCAB(createdConcept.getNid(), Snomed.SYNONYM_DESCRIPTION_TYPE.getNid(), LanguageCode.EN, mappingSet.getInverseName(),
						false, IdDirective.GENERATE_HASH);
				dCab.addAnnotationBlueprint(new RefexDynamicCAB(dCab.getComponentUuid(), ISAAC.ASSOCIATION_INVERSE_NAME.getPrimodialUuid()));
				OTFUtility.getBuilder().construct(dCab);
			}
			
			RefexDynamicCAB mappingAnnotation = new RefexDynamicCAB(rdud.getRefexUsageDescriptorNid(), MappingConstants.MAPPING_SEMEME_TYPE.getNid());
			mappingAnnotation.setData(new RefexDynamicDataBI[] {
					(mappingSet.getEditorStatus() == null ? null : new RefexDynamicUUID(mappingSet.getEditorStatus())),
					(StringUtils.isBlank(mappingSet.getPurpose()) ? null : new RefexDynamicString(mappingSet.getPurpose()))}, OTFUtility.getViewCoordinate());
			OTFUtility.getBuilder().construct(mappingAnnotation);
			
			RefexDynamicCAB associationAnnotation = new RefexDynamicCAB(rdud.getRefexUsageDescriptorNid(), ISAAC.ASSOCIATION_REFEX.getNid());
			associationAnnotation.setData(new RefexDynamicDataBI[] {}, null);
			OTFUtility.getBuilder().construct(associationAnnotation);
			
			ExtendedAppContext.getDataStore().addUncommitted(createdConcept);
			ExtendedAppContext.getDataStore().commit(createdConcept);
			
			//reread
			mappingConcept_ = OTFUtility.getConceptVersion(rdud.getRefexUsageDescriptorNid());
			
			//Find the constructed dynamic refset
			mappingRefexData_ = (RefexDynamicVersionBI<?>)ExtendedAppContext.getDataStore().getComponent(mappingAnnotation.getMemberUUID()).getVersion(OTFUtility.getViewCoordinate());
		
			
		}
		catch (ContradictionException | InvalidCAB | PropertyVetoException e)
		{
			throw new RuntimeException("Unexpected error creating mapping", e);
		}
		return null;
	}
	
	
	
}