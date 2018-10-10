package com.zmsk.face.pojo;

import java.util.ArrayList;
import java.util.List;

public class FaceEquipmentLibraryExample {
	protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public FaceEquipmentLibraryExample() {
		this.oredCriteria = new ArrayList();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return this.orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return this.distinct;
	}

	public List<Criteria> getOredCriteria() {
		return this.oredCriteria;
	}

	public void or(Criteria criteria) {
		this.oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		this.oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (this.oredCriteria.size() == 0) {
			this.oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		this.oredCriteria.clear();
		this.orderByClause = null;
		this.distinct = false;
	}

	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return this.condition;
		}

		public Object getValue() {
			return this.value;
		}

		public Object getSecondValue() {
			return this.secondValue;
		}

		public boolean isNoValue() {
			return this.noValue;
		}

		public boolean isSingleValue() {
			return this.singleValue;
		}

		public boolean isBetweenValue() {
			return this.betweenValue;
		}

		public boolean isListValue() {
			return this.listValue;
		}

		public String getTypeHandler() {
			return this.typeHandler;
		}

		protected Criterion(String condition) {
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if ((value instanceof List))
				this.listValue = true;
			else
				this.singleValue = true;
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	public static class Criteria extends FaceEquipmentLibraryExample.GeneratedCriteria {
	}

	protected static abstract class GeneratedCriteria {
		protected List<FaceEquipmentLibraryExample.Criterion> criteria;

		protected GeneratedCriteria() {
			this.criteria = new ArrayList();
		}

		public boolean isValid() {
			return this.criteria.size() > 0;
		}

		public List<FaceEquipmentLibraryExample.Criterion> getAllCriteria() {
			return this.criteria;
		}

		public List<FaceEquipmentLibraryExample.Criterion> getCriteria() {
			return this.criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			this.criteria.add(new FaceEquipmentLibraryExample.Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			this.criteria.add(new FaceEquipmentLibraryExample.Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if ((value1 == null) || (value2 == null)) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			this.criteria.add(new FaceEquipmentLibraryExample.Criterion(condition, value1, value2));
		}

		public FaceEquipmentLibraryExample.Criteria andIdIsNull() {
			addCriterion("id is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdIsNull() {
			addCriterion("library_id is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdIsNotNull() {
			addCriterion("library_id is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdEqualTo(Integer value) {
			addCriterion("library_id =", value, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdNotEqualTo(Integer value) {
			addCriterion("library_id <>", value, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdGreaterThan(Integer value) {
			addCriterion("library_id >", value, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("library_id >=", value, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdLessThan(Integer value) {
			addCriterion("library_id <", value, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdLessThanOrEqualTo(Integer value) {
			addCriterion("library_id <=", value, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdIn(List<Integer> values) {
			addCriterion("library_id in", values, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdNotIn(List<Integer> values) {
			addCriterion("library_id not in", values, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdBetween(Integer value1, Integer value2) {
			addCriterion("library_id between", value1, value2, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andLibraryIdNotBetween(Integer value1, Integer value2) {
			addCriterion("library_id not between", value1, value2, "libraryId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdIsNull() {
			addCriterion("equipment_id is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdIsNotNull() {
			addCriterion("equipment_id is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdEqualTo(Integer value) {
			addCriterion("equipment_id =", value, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdNotEqualTo(Integer value) {
			addCriterion("equipment_id <>", value, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdGreaterThan(Integer value) {
			addCriterion("equipment_id >", value, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("equipment_id >=", value, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdLessThan(Integer value) {
			addCriterion("equipment_id <", value, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdLessThanOrEqualTo(Integer value) {
			addCriterion("equipment_id <=", value, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdIn(List<Integer> values) {
			addCriterion("equipment_id in", values, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdNotIn(List<Integer> values) {
			addCriterion("equipment_id not in", values, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdBetween(Integer value1, Integer value2) {
			addCriterion("equipment_id between", value1, value2, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andEquipmentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("equipment_id not between", value1, value2, "equipmentId");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusIsNull() {
			addCriterion("sync_status is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusIsNotNull() {
			addCriterion("sync_status is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusEqualTo(Boolean value) {
			addCriterion("sync_status =", value, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusNotEqualTo(Boolean value) {
			addCriterion("sync_status <>", value, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusGreaterThan(Boolean value) {
			addCriterion("sync_status >", value, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusGreaterThanOrEqualTo(Boolean value) {
			addCriterion("sync_status >=", value, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusLessThan(Boolean value) {
			addCriterion("sync_status <", value, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusLessThanOrEqualTo(Boolean value) {
			addCriterion("sync_status <=", value, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusIn(List<Boolean> values) {
			addCriterion("sync_status in", values, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusNotIn(List<Boolean> values) {
			addCriterion("sync_status not in", values, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusBetween(Boolean value1, Boolean value2) {
			addCriterion("sync_status between", value1, value2, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andSyncStatusNotBetween(Boolean value1, Boolean value2) {
			addCriterion("sync_status not between", value1, value2, "syncStatus");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationIsNull() {
			addCriterion("operation is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationIsNotNull() {
			addCriterion("operation is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationEqualTo(Boolean value) {
			addCriterion("operation =", value, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationNotEqualTo(Boolean value) {
			addCriterion("operation <>", value, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationGreaterThan(Boolean value) {
			addCriterion("operation >", value, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationGreaterThanOrEqualTo(Boolean value) {
			addCriterion("operation >=", value, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationLessThan(Boolean value) {
			addCriterion("operation <", value, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationLessThanOrEqualTo(Boolean value) {
			addCriterion("operation <=", value, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationIn(List<Boolean> values) {
			addCriterion("operation in", values, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationNotIn(List<Boolean> values) {
			addCriterion("operation not in", values, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationBetween(Boolean value1, Boolean value2) {
			addCriterion("operation between", value1, value2, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andOperationNotBetween(Boolean value1, Boolean value2) {
			addCriterion("operation not between", value1, value2, "operation");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkIsNull() {
			addCriterion("remark is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkIsNotNull() {
			addCriterion("remark is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkEqualTo(String value) {
			addCriterion("remark =", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkNotEqualTo(String value) {
			addCriterion("remark <>", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkGreaterThan(String value) {
			addCriterion("remark >", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("remark >=", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkLessThan(String value) {
			addCriterion("remark <", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkLessThanOrEqualTo(String value) {
			addCriterion("remark <=", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkLike(String value) {
			addCriterion("remark like", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkNotLike(String value) {
			addCriterion("remark not like", value, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkIn(List<String> values) {
			addCriterion("remark in", values, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkNotIn(List<String> values) {
			addCriterion("remark not in", values, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkBetween(String value1, String value2) {
			addCriterion("remark between", value1, value2, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andRemarkNotBetween(String value1, String value2) {
			addCriterion("remark not between", value1, value2, "remark");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeIsNull() {
			addCriterion("ctime is null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeIsNotNull() {
			addCriterion("ctime is not null");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeEqualTo(Integer value) {
			addCriterion("ctime =", value, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeNotEqualTo(Integer value) {
			addCriterion("ctime <>", value, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeGreaterThan(Integer value) {
			addCriterion("ctime >", value, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeGreaterThanOrEqualTo(Integer value) {
			addCriterion("ctime >=", value, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeLessThan(Integer value) {
			addCriterion("ctime <", value, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeLessThanOrEqualTo(Integer value) {
			addCriterion("ctime <=", value, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeIn(List<Integer> values) {
			addCriterion("ctime in", values, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeNotIn(List<Integer> values) {
			addCriterion("ctime not in", values, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeBetween(Integer value1, Integer value2) {
			addCriterion("ctime between", value1, value2, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}

		public FaceEquipmentLibraryExample.Criteria andCtimeNotBetween(Integer value1, Integer value2) {
			addCriterion("ctime not between", value1, value2, "ctime");
			return (FaceEquipmentLibraryExample.Criteria) this;
		}
	}
}