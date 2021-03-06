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
package gov.va.isaac.gui.dragAndDrop;

import java.util.Optional;
import java.util.UUID;

import gov.va.isaac.util.Utility;

/**
 * {@link SingleConceptIdProvider}
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
@FunctionalInterface
public interface SingleConceptIdProvider extends ConceptIdProvider
{
	/**
	 * Implementers of this should return a UUID, or a NID - which we would expect most drop targets to handle.
	 * @return
	 */
	public String getConceptId();
	
	/**
	 * Convenience method
	 * @return
	 */
	default public boolean isSctId()
	{
		return getSctId().isPresent();
	}
	
	/**
	 * Convenience method
	 * @return
	 */
	default public boolean isNid()
	{
		return Utility.isInt(getConceptId());
	}
	
	/**
	 * Convenience method
	 * @return
	 */
	default public boolean isUUID()
	{
		return Utility.isUUID(getConceptId());
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.dragAndDrop.ConceptIdProvider#getSctId()
	 */
	@Override
	default public Optional<Long> getSctId()
	{
		return Optional.empty();
	}
	
	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.dragAndDrop.ConceptIdProvider#getUUID()
	 */
	@Override
	default public UUID getUUID()
	{
		return UUID.fromString(getConceptId());
	}
	
	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.dragAndDrop.ConceptIdProvider#getNid()
	 */
	@Override
	default public Integer getNid()
	{
		return Integer.parseInt(getConceptId());
	}
}
