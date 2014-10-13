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
package gov.va.isaac.interfaces.gui.views;

import java.util.UUID;

import org.jvnet.hk2.annotations.Contract;

/**
 * {@link TaskWithConceptViewI}
 * 
 * An interface that allows the creation of an TaskWithConceptViewI implementation,
 * which will be a JavaFX component that extends/implements {@link ViewI}.
 * This panel is intended to allow display and manipulation of
 * a specified existing workflow task, which itself contains a displayable concept

 * @author <a href="mailto:jkniaz@apelon.com">Joel Kniaz</a> 
 */
@Contract
public interface TaskWithConceptViewI extends ViewI
{   
	/**
	 * @param taskId the long id of the workflow task to display 
	 */
	public void setTask(long taskId);
	
	/**
	 * @return the long id of the workflow displayed task
	 */
	public Long getTask();

	/**
	 * Get the selected concept as UUID.
	 * @return conceptUuid the selected concept as UUID
	 */
	public UUID getConceptUuid();
	
	/**
	 * Get the selected concept as int.
	 * @return conceptUuid the selected concept as int
	 */
	public int getConceptNid();
}
