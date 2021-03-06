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
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * LuceneSearchTypeFilter
 * 
 * @author <a href="mailto:joel.kniaz@gmail.com">Joel Kniaz</a>
 */
package gov.va.isaac.gui.enhancedsearchview.filters;

import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;

import gov.va.isaac.gui.enhancedsearchview.SearchTypeEnums.ComponentSearchType;
import gov.va.isaac.gui.enhancedsearchview.SearchTypeEnums.SearchType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SememeContentSearchTypeFilter extends SearchTypeFilter<SememeContentSearchTypeFilter> {
	final private ObjectProperty<ConceptVersionBI> assemblageConcept;

	public SememeContentSearchTypeFilter() {
		this(new SimpleStringProperty(), new SimpleObjectProperty<>());
	}
	public SememeContentSearchTypeFilter(String param, ConceptVersionBI assemblage) {
		this(new SimpleStringProperty(param), new SimpleObjectProperty<>(assemblage));
	}
	public SememeContentSearchTypeFilter(StringProperty param, ObjectProperty<ConceptVersionBI> assemblage) {
		super(param);

		this.assemblageConcept = assemblage != null ? assemblage : new SimpleObjectProperty<>();

		searchParameter.addListener(new ChangeListener<String>() {
			@Override
			public void changed(
					ObservableValue<? extends String> observable,
					String oldValue,
					String newValue) {
				if (isValid()) {
					isValid.set(true);
				} else {
					isValid.set(false);
				}
			}
		});
	}

	@Override
	public String toString() {
		return "SememeContentSearchTypeFilter [searchParameter=" + searchParameter.get()+ ", assemblage=" + (assemblageConcept.get() != null ? assemblageConcept.get().getPrimordialUuid() : null)
				+ ", isValid=" + isValid.get() + "]";
	}
	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.enhancedsearchview.filters.Filter#isValidProperty()
	 */
	@Override
	public BooleanProperty isValidProperty() {
		return isValid;
	}

	public ConceptVersionBI getAssemblageConcept() {
		return assemblageConcept.get();
	}
	public void setAssemblageConcept(ConceptVersionBI concept) {
		this.assemblageConcept.set(concept);
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.enhancedsearchview.filters.Filter#copy(gov.va.isaac.gui.enhancedsearchview.filters.Filter)
	 */
	@Override
	public void copy(SememeContentSearchTypeFilter toCopy) {
		searchParameter.set(toCopy.getSearchParameter());
		assemblageConcept.set(toCopy.assemblageConcept.get());
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.enhancedsearchview.filters.SearchTypeFilter#getSearchType()
	 */
	@Override
	public ComponentSearchType getComponentSearchType() {
		return ComponentSearchType.LUCENE;
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.enhancedsearchview.filters.SearchTypeFilter#getSearchType()
	 */
	@Override
	public SearchType getSearchType() {
		return SearchType.SEMEME;
	}

	/* (non-Javadoc)
	 * @see gov.va.isaac.gui.enhancedsearchview.filters.Filter#isValid()
	 */
	@Override
	public boolean isValid() {
		return searchParameter.get() != null && searchParameter.get().trim().length() > 0;
	}
}