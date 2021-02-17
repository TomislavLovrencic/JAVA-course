package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**
 * @author Tomislav Lovrencic
 * This class represnts a implementation of {@link IFilter} that is used to accept records based on queries in this class.
 */
public class QueryFilter implements IFilter {

	
	/**
	 *  list of queries.
	 */
	private List<ConditionalExpression> lista;
	
	/** This is a basic constructor.
	 * @param lista
	 */
	public QueryFilter(List<ConditionalExpression> lista) {
		this.lista = lista;
	}
	
	@Override
	public boolean accepts(StudentRecord record) {
		for(ConditionalExpression exp : lista) {
			if(exp.getFieldValueGetter().equals(FieldValueGetters.FIRST_NAME)) {
				if(!exp.getComparisonOperator().satisfied(record.getFirstName(), exp.getStr())){
					return false;
				}
				
			}
			else if(exp.getFieldValueGetter().equals(FieldValueGetters.LAST_NAME)) {
				if(!exp.getComparisonOperator().satisfied(record.getLastName(), exp.getStr())) {
					return false;
				}
	
				
			}
			else if(exp.getFieldValueGetter().equals(FieldValueGetters.JMBAG)) {
				if(!exp.getComparisonOperator().satisfied(record.getJmbag(), exp.getStr())){
					return false;
				}
			
			}
		}
		return true;
	}

}
