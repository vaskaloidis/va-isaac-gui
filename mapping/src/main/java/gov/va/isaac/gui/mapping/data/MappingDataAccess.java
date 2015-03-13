/**
 * Copyright Notice
 *
 * This is a work of the U.S. Government and is not subject to copyright 
 * protection in the United States. Foreign copyrights may apply.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.va.isaac.gui.mapping.data;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.constants.MappingConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.lucene.queryparser.classic.ParseException;
import org.ihtsdo.otf.query.lucene.LuceneDescriptionIndexer;
import org.ihtsdo.otf.query.lucene.LuceneDynamicRefexIndexer;
import org.ihtsdo.otf.tcc.api.blueprint.ComponentProperty;
import org.ihtsdo.otf.tcc.api.refexDynamic.RefexDynamicChronicleBI;
import org.ihtsdo.otf.tcc.model.index.service.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link MappingDataAccess}
 *
 * Various utility methods to read and write Mapping related data
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
public class MappingDataAccess
{
	private static final Logger LOG = LoggerFactory.getLogger(MappingDataAccess.class);
	/**
	 * Convenience method to fetch all mappings (defined as instances of the sememe @{link {@link MappingConstants#MAPPING_SEMEME_TYPE}
	 * @return the mappings.  Will not return null.
	 * @throws IOException 
	 */
	public static List<MappingSet> getMappingSets() throws IOException {
		return getMappingSets(false);
	}
	
	public static List<MappingSet> getMappingSets(boolean activeOnly) throws IOException
	{
		//TODO implement Active Only
		try
		{
			ArrayList<MappingSet> result = new ArrayList<>();
			
			LuceneDynamicRefexIndexer indexer = AppContext.getService(LuceneDynamicRefexIndexer.class);
			if (indexer == null)
			{
				throw new RuntimeException("Required index is not available");
			}
			List<SearchResult> refexes = indexer.queryAssemblageUsage(MappingConstants.MAPPING_SEMEME_TYPE.getNid(), 5000, Long.MAX_VALUE);
			for (SearchResult sr : refexes)
			{
				RefexDynamicChronicleBI<?> rc = (RefexDynamicChronicleBI<?>) ExtendedAppContext.getDataStore().getComponent(sr.getNid());
				result.add(new MappingSet(rc));
			}
			
			return result;
		}
		catch (NumberFormatException | ParseException e)
		{
			LOG.error("Unexpected error reading mappings", e);
			throw new IOException("Error reading mappings", e);
		}
	}
	
}
