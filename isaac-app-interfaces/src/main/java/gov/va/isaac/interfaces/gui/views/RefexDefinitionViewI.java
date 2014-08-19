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

import org.jvnet.hk2.annotations.Contract;

/**
 * {@link RefexDefinitionViewI}
 * 
 * An interface that allows the creation of a RefexDefinitionView implementation, which 
 * will be a JavaFX component that extends {@link DockedViewI}.  This docked view is intended 
 * to let the user browse and/or filter the Refexes in the system
 *
 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a> 
 */
@Contract
public interface RefexDefinitionViewI extends DockedViewI
{

}