/**
 * Copyright Notice
 *
 * This is a work of the U.S. Government and is not subject to copyright
 * protection in the United States. Foreign copyrights may apply.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package gov.va.isaac.config.users;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.config.generated.IsaacUserCreation;
import gov.va.isaac.config.generated.User;
import gov.va.isaac.config.profiles.UserProfileManager;
import gov.vha.isaac.metadata.coordinates.EditCoordinates;
import gov.vha.isaac.metadata.coordinates.LogicCoordinates;
import gov.vha.isaac.metadata.coordinates.ViewCoordinates;
import gov.vha.isaac.metadata.source.IsaacMetadataAuxiliaryBinding;
import gov.vha.isaac.ochre.api.ConceptModel;
import gov.vha.isaac.ochre.api.Get;
import gov.vha.isaac.ochre.api.LookupService;
import gov.vha.isaac.ochre.api.commit.ChangeCheckerMode;
import gov.vha.isaac.ochre.api.component.concept.ConceptBuilder;
import gov.vha.isaac.ochre.api.component.concept.ConceptBuilderService;
import gov.vha.isaac.ochre.api.component.concept.ConceptChronology;
import gov.vha.isaac.ochre.api.component.concept.description.DescriptionBuilder;
import gov.vha.isaac.ochre.api.component.concept.description.DescriptionBuilderService;
import gov.vha.isaac.ochre.api.logic.LogicalExpression;
import gov.vha.isaac.ochre.api.logic.LogicalExpressionBuilder;
import static gov.vha.isaac.ochre.api.logic.LogicalExpressionBuilder.And;
import static gov.vha.isaac.ochre.api.logic.LogicalExpressionBuilder.ConceptAssertion;
import static gov.vha.isaac.ochre.api.logic.LogicalExpressionBuilder.NecessarySet;
import gov.vha.isaac.ochre.api.logic.LogicalExpressionBuilderService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.UUID;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.ihtsdo.otf.tcc.api.blueprint.ComponentProperty;
import org.ihtsdo.otf.tcc.api.blueprint.ConceptCB;
import org.ihtsdo.otf.tcc.api.blueprint.DescriptionCAB;
import org.ihtsdo.otf.tcc.api.blueprint.IdDirective;
import org.ihtsdo.otf.tcc.api.blueprint.InvalidCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexCAB;
import org.ihtsdo.otf.tcc.api.blueprint.RefexDirective;
import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.coordinate.EditCoordinate;
import org.ihtsdo.otf.tcc.api.lang.LanguageCode;
import org.ihtsdo.otf.tcc.api.refex.RefexType;
import org.ihtsdo.otf.tcc.api.store.TerminologyStoreDI;
import gov.vha.isaac.ochre.util.UuidT5Generator;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * {@link GenerateUsers}
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
 */
public class GenerateUsers {

    public static final UUID USER_LOGON_NAMESPACE = UUID.fromString("e3992850-7204-5159-af93-c0027a79d23c");
    private static Logger logger = LoggerFactory.getLogger(GenerateUsers.class);

    public static void generateUsers() throws InvalidUserException {
        buildMissingUsers(readUserCreationFile());
    }

    public static void generateUsers(File sourceFile) throws FileNotFoundException, InvalidUserException {
        generateUsers(new FileInputStream(sourceFile));
    }

    public static void generateUsers(InputStream is) throws InvalidUserException {
        buildMissingUsers(readUserCreationFile(is));
    }

    public static IsaacUserCreation readUserCreationFile() throws InvalidUserException {
        return readUserCreationFile(AppContext.class.getResourceAsStream("/users.xml"));
    }

    public static IsaacUserCreation readUserCreationFile(File sourceFile) throws FileNotFoundException, InvalidUserException {
        return readUserCreationFile(new FileInputStream(sourceFile));
    }

    public static IsaacUserCreation readUserCreationFile(InputStream is) throws InvalidUserException {
        if (is == null) {
            throw new RuntimeException("Failure reading the users file!");
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IsaacUserCreation.class);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(AppContext.class.getResourceAsStream("/xsd/UserGenerationSchema.xsd")));
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setSchema(schema);
            IsaacUserCreation iuc = (IsaacUserCreation) jaxbUnmarshaller.unmarshal(is);
            is.close();
            return iuc;
        } catch (JAXBException | SAXException | IOException e) {
            logger.error("Unexpected error processing users file", e);
            throw new RuntimeException("Unexpected error processing users file");
        }
    }

    private static void buildMissingUsers(IsaacUserCreation iuc) throws InvalidUserException {
        try {
            UserProfileManager upm = AppContext.getService(UserProfileManager.class);

			//This bit of hashing is to ensure that they didn't repeat any userLogin names (which can't be duplicated
            //for obvious reasons) and to make sure that the UniqueFullName is unique, which is used as the FSN, and as 
            //the basis for the computed UUID for the user concept (if they don't provide their own UUID)
            HashSet<String> uniqueFullNames = new HashSet<>();
            HashSet<String> uniqueLogonNames = new HashSet<>();
            HashSet<String> usersToSkip = new HashSet<>();
            for (User user : iuc.getUser()) {
                if (!uniqueFullNames.add(user.getUniqueFullName())) {
                    usersToSkip.add(user.getUniqueLogonName());
                }
                if (!uniqueLogonNames.add(user.getUniqueLogonName())) {
                    usersToSkip.add(user.getUniqueLogonName());
                }
            }

            for (User user : iuc.getUser()) {
                logger.debug("Checking user " + toString(user));

                if (usersToSkip.contains(user.getUniqueLogonName())) {
                    logger.error("Skipping the user {} because the uniqueLogonName and/or the uniqueFullName is duplicated within the users file.", toString(user));
                    continue;
                }

				//This also validates other rules about the incoming user, to make sure it can be created - throws an exception, if the user 
                //is invalid for whatever reason.  This also populates the UUID field (if necessary)
                if (alreadyExists(user)) {
                    logger.debug("User already exists in DB");
                } else {
                    createUserConcept(user);
                }

                if (!upm.doesProfileExist(user.getUniqueLogonName())) {
                    upm.createUserProfile(user, iuc.getNewUserDefaults());
                } else {
                    logger.debug("User profile already exists");
                }
            }
        } catch (ContradictionException | IOException | InvalidCAB e) {
            logger.error("Unexpected error building the user concepts", e);
            throw new RuntimeException("Unexpected error building user concepts", e);
        } catch (InvalidUserException e) {
            throw e;
        }
    }

    /**
     * Create a concept in the DB, for the specified user. Only call this if {@link #alreadyExists(User)) return false
     */
    public static void createUserConcept(User user) throws IOException, InvalidCAB, ContradictionException {
        logger.info("Creating user " + toString(user) + " in DB");
        AppContext.getRuntimeGlobals().disableAllCommitListeners();
        try {
            if (Get.conceptModel() == ConceptModel.OCHRE_CONCEPT_MODEL) {
                createOchreUserConcept(user);
            } else {
                createOtfUserConcept(user);
            }

        } finally {
            AppContext.getRuntimeGlobals().enableAllCommitListeners();
        }
    }

    private static void createOchreUserConcept(User user) {
        ConceptBuilderService conceptBuilderService = LookupService.getService(ConceptBuilderService.class);
        conceptBuilderService.setDefaultLanguageForDescriptions(IsaacMetadataAuxiliaryBinding.ENGLISH);
        conceptBuilderService.setDefaultDialectAssemblageForDescriptions(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        conceptBuilderService.setDefaultLogicCoordinate(LogicCoordinates.getStandardElProfile());

        DescriptionBuilderService descriptionBuilderService = LookupService.getService(DescriptionBuilderService.class);
        LogicalExpressionBuilderService expressionBuilderService
                = LookupService.getService(LogicalExpressionBuilderService.class);
        LogicalExpressionBuilder defBuilder = expressionBuilderService.getLogicalExpressionBuilder();

        NecessarySet(And(ConceptAssertion(Get.conceptService().getConcept(IsaacMetadataAuxiliaryBinding.USER.getConceptSequence()), defBuilder)));

        LogicalExpression userDef = defBuilder.build();

        ConceptBuilder builder = conceptBuilderService.getDefaultConceptBuilder(user.getUniqueFullName(), "user", userDef);
        builder.setPrimordialUuid(UUID.fromString(user.getUUID()));

        DescriptionBuilder definitionBuilder = descriptionBuilderService.
                getDescriptionBuilder(user.getUniqueLogonName(), builder,
                        IsaacMetadataAuxiliaryBinding.SYNONYM,
                        IsaacMetadataAuxiliaryBinding.ENGLISH);

        definitionBuilder.setPreferredInDialectAssemblage(IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT);
        builder.addDescription(definitionBuilder);

        List createdComponents = new ArrayList();

        ConceptChronology newCon = builder.build(EditCoordinates.getDefaultUserSolorOverlay(), ChangeCheckerMode.ACTIVE, createdComponents);

        Get.commitService().addUncommitted(newCon);

        Get.commitService().commit("GenerateUsers adding: " + user.getUniqueFullName());

    }

    private static void createOtfUserConcept(User user) throws InvalidCAB, IOException, ContradictionException {
        TerminologyStoreDI ts = ExtendedAppContext.getDataStore();
        String fsn = user.getUniqueFullName();
        String preferredName = user.getFullName();
        String logonName = user.getUniqueLogonName();
        UUID userUUID = UUID.fromString(user.getUUID());

        LanguageCode lc = LanguageCode.EN_US;
        UUID isA = IsaacMetadataAuxiliaryBinding.IS_A.getPrimodialUuid();
        IdDirective idDir = IdDirective.PRESERVE;
        UUID module = IsaacMetadataAuxiliaryBinding.ISAAC_MODULE.getPrimodialUuid();
        UUID parents[] = new UUID[]{IsaacMetadataAuxiliaryBinding.USER.getPrimodialUuid()};

        ConceptCB cab = new ConceptCB(fsn, preferredName, lc, isA, idDir, module, IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getPrimodialUuid(), userUUID, parents);

        DescriptionCAB dCab = new DescriptionCAB(cab.getComponentUuid(), IsaacMetadataAuxiliaryBinding.SYNONYM.getPrimodialUuid(), lc, logonName, true,
                IdDirective.GENERATE_HASH);
        dCab.getProperties().put(ComponentProperty.MODULE_ID, module);

        //Mark it as acceptable
        RefexCAB rCabAcceptable = new RefexCAB(RefexType.CID, dCab.getComponentUuid(), IsaacMetadataAuxiliaryBinding.US_ENGLISH_DIALECT.getPrimodialUuid(),
                IdDirective.GENERATE_HASH, RefexDirective.EXCLUDE);
        rCabAcceptable.put(ComponentProperty.COMPONENT_EXTENSION_1_ID, IsaacMetadataAuxiliaryBinding.ACCEPTABLE.getPrimodialUuid());
        rCabAcceptable.getProperties().put(ComponentProperty.MODULE_ID, module);
        dCab.addAnnotationBlueprint(rCabAcceptable);

        cab.addDescriptionCAB(dCab);

        //TODO store roles on the concept
        //Build this on the lowest level path, otherwise, other code that references this will fail (as it doesn't know about custom paths)
        ConceptChronicleBI newCon = ts.getTerminologyBuilder(
                new EditCoordinate(IsaacMetadataAuxiliaryBinding.USER.getLenient().getConceptNid(), IsaacMetadataAuxiliaryBinding.ISAAC_MODULE.getLenient().getNid(),
                        IsaacMetadataAuxiliaryBinding.DEVELOPMENT.getLenient().getConceptNid()), ViewCoordinates.getMetadataViewCoordinate()).construct(cab);
        ts.addUncommitted(newCon);
        ts.commit();  //TODO OCHRE change back to a concept commit
    }

    /**
     * Check if the user already exists in the DB (return true) and if not,
     * validate the incoming parameters, throwing an exception if anything is
     * amiss with the user definition.
     *
     * As a side effect, this populates the UUID field of the incoming user, if
     * it was not yet populated. Also populates the password field (with the
     * logon name) if it was not populated. Also populates the FullName field
     * with the UniqueFullName field if it was not populated
     *
     * @throws InvalidUserException for any issues with the values provided
     * within the user
     */
    public static boolean alreadyExists(User user) throws InvalidUserException {

        if (user.getUniqueFullName() == null || user.getUniqueFullName().length() == 0) {
            throw new InvalidUserException("The uniqueFullName value is required.", user);
        }

        if (user.getUniqueLogonName() == null || user.getUniqueLogonName().length() == 0) {
            throw new InvalidUserException("The uniqueLogonName value is required.", user);
        }

        UUID uuid;

        if (user.getUUID() == null || user.getUUID().length() == 0) {
            uuid = calculateUserUUID(user.getUniqueFullName());
            user.setUUID(uuid.toString());
        } else {
            try {
                uuid = UUID.fromString(user.getUUID());
            } catch (Exception e) {
                throw new InvalidUserException("The specified UUID is not a valid UUID", user);
            }
        }

        if (user.getFullName() == null || user.getFullName().length() == 0) {
            user.setFullName(user.getUniqueFullName());
        }

        if (user.getPassword() == null || user.getPassword().length() == 0) {
            user.setPassword(user.getUniqueLogonName());
        }

        return ExtendedAppContext.getDataStore().hasConcept(uuid);
    }

    public static UUID calculateUserUUID(String uniqueLogonName) {
        return UuidT5Generator.get(USER_LOGON_NAMESPACE, uniqueLogonName);
    }

    static String toString(User user) {
        return "uniqueFullName: " + user.getUniqueFullName() + " uniqueLogonName: " + user.getUniqueLogonName() + " fullName: " + user.getFullName() + " UUID: "
                + user.getUUID();
    }
}
