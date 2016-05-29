package com.hamburgueria.morphia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.hamburgueria.config.EnvConfig;
import com.hamburgueria.constants.QueriesConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.DomainSuperClass;
import com.hamburgueria.mongo.entities.Sequence;
import com.hamburgueria.morphia.db.MorphiaDS;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;


@SuppressWarnings("unchecked")
public class BaseMongoDao<MODEL>{
	
	private Class<?> classe;
	private MorphiaDS morphia;
	
	public BaseMongoDao (){
	}
	
	public BaseMongoDao (Class<?> classe){
		this.classe = classe;
		morphia = MorphiaDS.getinstance(EnvConfig.LOCAL, false);
	}

	public Datastore getDs() {
		return morphia.getDataStore();
	}

	public long save(MODEL model){
		return (Long) getDs().save(model).getId();
	}

	public long getCounterSeq() {
		String sequenceName = classe.getSimpleName().toLowerCase()+QueriesConstants.SEQUENCE_SUFIX;
		UpdateOperations<Sequence> inc = getDs().createUpdateOperations(Sequence.class).inc("counter", 1);
		Query<Sequence> query = getDs().createQuery(Sequence.class).field(QueriesConstants.ID_FIELD).
				equal(classe.getSimpleName().toLowerCase()+QueriesConstants.SEQUENCE_SUFIX);
		Sequence counter = getDs().findAndModify(query, inc, true);
//		CREATE SEQUENCE ON FIRST PERSIST
		if(counter == null){
			Sequence seq = new Sequence();
			seq.set_id(sequenceName);
			seq.setCounter(1);
			getDs().save(seq);
			return getCounterSeq();
		}
		return counter.getCounter();
	}
	
	public List<String> listDistinct(String field){
		DBCollection collection = getDs().getCollection(classe);
		return (List<String>)collection.distinct(field);
	}
	
	public MODEL getById(Long _id){
		return (MODEL) getDs().createQuery(classe).field("_id").equal(_id).get();
	}
	
	public List<MODEL> listAll(){
		Query<MODEL> query = (Query<MODEL>) getDs().createQuery(classe);
		return query.asList();
	}
	
	public List<MODEL> getByRegex(String field, String value){
		Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
		return getModelByFilter(field, pattern);
	}
	
	
	public MODEL getModelByQuery(Query<MODEL> query){
		return query.get();
	}
	public List<MODEL> getListByQuery(Query<MODEL> query){
		return (List<MODEL>) query.asList();
	}
	
	public List<MODEL> getModelByfieldLessThanOrEqual(String fieldName,String value){
		return (List<MODEL>) getDs().createQuery(classe).field(fieldName).lessThanOrEq(value).asList();
	}
	
	public List<MODEL> getModelByfieldLessThan(String fieldName,String value){
		return (List<MODEL>) getDs().createQuery(classe).field(fieldName).lessThan(value).asList();
	}
	
	public List<MODEL> getModelByfieldGreaterThanOrEqual(String fieldName,String value){
		return (List<MODEL>) getDs().createQuery(classe).field(fieldName).greaterThanOrEq(value).asList();
	}
	
	public List<MODEL> getModelByfieldGreaterThan(String fieldName,String value){
		return (List<MODEL>) getDs().createQuery(classe).field(fieldName).greaterThan(value).asList();
	}
	
	public List<MODEL> getModelByfield(String fieldName,Object value){
		return (List<MODEL>) getDs().createQuery(classe).field(fieldName).equal(value).asList();
	}
	public List<MODEL> getModelByFilter(String filter,Object value){
		return (List<MODEL>) getDs().createQuery(classe).filter(filter, value).asList();
	}
	
	public int delete(Long id){
		MODEL model = getById(id);
		return getDs().delete(model).getN();
	}
	public int deleteModel(MODEL model){
		return getDs().delete(model).getN();
	}

	public long saveOrUpdate(MODEL model) throws MongoException{
		if(model instanceof DomainSuperClass){
			
			DomainSuperClass domain = (DomainSuperClass) model;
			if(getById(domain.get_id()) == null ){
				domain.set_id(getCounterSeq());
				domain.setDateCadastro(new Date());
			}
			return save((MODEL)domain);
		}
		return -1;
	}
	
	public List<MODEL> getByComplexQueryAnd(Map<String, Object> fieldValuePairs){
		Query<?> query = getDs().createQuery(classe);
		for(String key : fieldValuePairs.keySet()){
			if(fieldValuePairs.get(key) != null){
				query.field(key).equal(fieldValuePairs.get(key));
			}
		}
		return (List<MODEL>) query.asList();
	}
	
	public MODEL getModelByComplexQueryAnd(Map<String, Object> fieldValuePairs){
		Query<?> query = getDs().createQuery(classe);
		for(String key : fieldValuePairs.keySet()){
			query.field(key).equal(fieldValuePairs.get(key));
		}
		return (MODEL) query.get();
	}
	
	public List<MODEL> getByDateRange(String DateFieldName, Date inDt, Date endDt){
		return (List<MODEL>) getDs().createQuery(classe)
				.field(DateFieldName).greaterThanOrEq(inDt)
				.field(DateFieldName).lessThanOrEq(endDt).asList();
	}
	
	public List<MODEL> getAllOnlyFields(boolean retrieve, String fieldsQuery){
	    	String[] fields = fieldsQuery.split(ServiceConstants.QUERY_SEPARATOR);
	    	Query<?> query = getDs().createQuery(classe);
	    	if(fields.length > 0){
	    		query.retrievedFields(retrieve, fields);
	    	}else
	    		query.retrievedFields(retrieve, fieldsQuery);
		return (List<MODEL>) query.asList();
	}
	
	public MODEL updateFieldById(String fieldName, Object fieldValue, Long id){
		UpdateOperations<MODEL> updateOp = (UpdateOperations<MODEL>) getDs().createUpdateOperations(classe);
		updateOp.set(fieldName, fieldValue).enableValidation();
		Query<MODEL> query = (Query<MODEL>) getDs().createQuery(classe).field(Mapper.ID_KEY).equal(id);
		return (MODEL) getDs().update(query, updateOp);
	}
	
}
