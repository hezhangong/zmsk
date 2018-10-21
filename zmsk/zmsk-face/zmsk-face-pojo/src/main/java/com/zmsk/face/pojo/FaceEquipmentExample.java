package com.zmsk.face.pojo;

import java.util.ArrayList;
import java.util.List;

public class FaceEquipmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FaceEquipmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("a.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("a.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("a.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("a.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("a.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("a.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("a.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("a.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("a.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("a.organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("a.organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(Integer value) {
            addCriterion("a.organization_id =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(Integer value) {
            addCriterion("a.organization_id <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(Integer value) {
            addCriterion("a.organization_id >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.organization_id >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(Integer value) {
            addCriterion("a.organization_id <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.organization_id <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<Integer> values) {
            addCriterion("a.organization_id in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<Integer> values) {
            addCriterion("a.organization_id not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(Integer value1, Integer value2) {
            addCriterion("a.organization_id between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.organization_id not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andMacIdIsNull() {
            addCriterion("a.mac_id is null");
            return (Criteria) this;
        }

        public Criteria andMacIdIsNotNull() {
            addCriterion("a.mac_id is not null");
            return (Criteria) this;
        }

        public Criteria andMacIdEqualTo(String value) {
            addCriterion("a.mac_id =", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotEqualTo(String value) {
            addCriterion("a.mac_id <>", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdGreaterThan(String value) {
            addCriterion("a.mac_id >", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdGreaterThanOrEqualTo(String value) {
            addCriterion("a.mac_id >=", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLessThan(String value) {
            addCriterion("a.mac_id <", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLessThanOrEqualTo(String value) {
            addCriterion("a.mac_id <=", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLike(String value) {
            addCriterion("a.mac_id like", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotLike(String value) {
            addCriterion("a.mac_id not like", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdIn(List<String> values) {
            addCriterion("a.mac_id in", values, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotIn(List<String> values) {
            addCriterion("a.mac_id not in", values, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdBetween(String value1, String value2) {
            addCriterion("a.mac_id between", value1, value2, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotBetween(String value1, String value2) {
            addCriterion("a.mac_id not between", value1, value2, "macId");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberIsNull() {
            addCriterion("a.equipment_number is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberIsNotNull() {
            addCriterion("a.equipment_number is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberEqualTo(String value) {
            addCriterion("a.equipment_number =", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotEqualTo(String value) {
            addCriterion("a.equipment_number <>", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberGreaterThan(String value) {
            addCriterion("a.equipment_number >", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberGreaterThanOrEqualTo(String value) {
            addCriterion("a.equipment_number >=", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberLessThan(String value) {
            addCriterion("a.equipment_number <", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberLessThanOrEqualTo(String value) {
            addCriterion("a.equipment_number <=", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberLike(String value) {
            addCriterion("a.equipment_number like", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotLike(String value) {
            addCriterion("a.equipment_number not like", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberIn(List<String> values) {
            addCriterion("a.equipment_number in", values, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotIn(List<String> values) {
            addCriterion("a.equipment_number not in", values, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberBetween(String value1, String value2) {
            addCriterion("a.equipment_number between", value1, value2, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotBetween(String value1, String value2) {
            addCriterion("a.equipment_number not between", value1, value2, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdIsNull() {
            addCriterion("a.equipment_pwd is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdIsNotNull() {
            addCriterion("a.equipment_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdEqualTo(String value) {
            addCriterion("a.equipment_pwd =", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotEqualTo(String value) {
            addCriterion("a.equipment_pwd <>", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdGreaterThan(String value) {
            addCriterion("a.equipment_pwd >", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdGreaterThanOrEqualTo(String value) {
            addCriterion("a.equipment_pwd >=", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdLessThan(String value) {
            addCriterion("a.equipment_pwd <", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdLessThanOrEqualTo(String value) {
            addCriterion("a.equipment_pwd <=", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdLike(String value) {
            addCriterion("a.equipment_pwd like", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotLike(String value) {
            addCriterion("a.equipment_pwd not like", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdIn(List<String> values) {
            addCriterion("a.equipment_pwd in", values, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotIn(List<String> values) {
            addCriterion("a.equipment_pwd not in", values, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdBetween(String value1, String value2) {
            addCriterion("a.equipment_pwd between", value1, value2, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotBetween(String value1, String value2) {
            addCriterion("a.equipment_pwd not between", value1, value2, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdIsNull() {
            addCriterion("a.equipment_plain_pwd is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdIsNotNull() {
            addCriterion("a.equipment_plain_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdEqualTo(String value) {
            addCriterion("a.equipment_plain_pwd =", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotEqualTo(String value) {
            addCriterion("a.equipment_plain_pwd <>", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdGreaterThan(String value) {
            addCriterion("a.equipment_plain_pwd >", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdGreaterThanOrEqualTo(String value) {
            addCriterion("a.equipment_plain_pwd >=", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdLessThan(String value) {
            addCriterion("a.equipment_plain_pwd <", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdLessThanOrEqualTo(String value) {
            addCriterion("a.equipment_plain_pwd <=", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdLike(String value) {
            addCriterion("a.equipment_plain_pwd like", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotLike(String value) {
            addCriterion("a.equipment_plain_pwd not like", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdIn(List<String> values) {
            addCriterion("a.equipment_plain_pwd in", values, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotIn(List<String> values) {
            addCriterion("a.equipment_plain_pwd not in", values, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdBetween(String value1, String value2) {
            addCriterion("a.equipment_plain_pwd between", value1, value2, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotBetween(String value1, String value2) {
            addCriterion("a.equipment_plain_pwd not between", value1, value2, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeIsNull() {
            addCriterion("a.equipment_type is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeIsNotNull() {
            addCriterion("a.equipment_type is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeEqualTo(Boolean value) {
            addCriterion("a.equipment_type =", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeNotEqualTo(Boolean value) {
            addCriterion("a.equipment_type <>", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeGreaterThan(Boolean value) {
            addCriterion("a.equipment_type >", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("a.equipment_type >=", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeLessThan(Boolean value) {
            addCriterion("a.equipment_type <", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("a.equipment_type <=", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeIn(List<Boolean> values) {
            addCriterion("a.equipment_type in", values, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeNotIn(List<Boolean> values) {
            addCriterion("a.equipment_type not in", values, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("a.equipment_type between", value1, value2, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("a.equipment_type not between", value1, value2, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeIsNull() {
            addCriterion("a.renewal_fee is null");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeIsNotNull() {
            addCriterion("a.renewal_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeEqualTo(Integer value) {
            addCriterion("a.renewal_fee =", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeNotEqualTo(Integer value) {
            addCriterion("a.renewal_fee <>", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeGreaterThan(Integer value) {
            addCriterion("a.renewal_fee >", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.renewal_fee >=", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeLessThan(Integer value) {
            addCriterion("a.renewal_fee <", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeLessThanOrEqualTo(Integer value) {
            addCriterion("a.renewal_fee <=", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeIn(List<Integer> values) {
            addCriterion("a.renewal_fee in", values, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeNotIn(List<Integer> values) {
            addCriterion("a.renewal_fee not in", values, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeBetween(Integer value1, Integer value2) {
            addCriterion("a.renewal_fee between", value1, value2, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("a.renewal_fee not between", value1, value2, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("a.status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("a.status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("a.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("a.status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("a.status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("a.status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("a.status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("a.status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("a.status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("a.status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("a.status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("a.status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("a.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("a.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Integer value) {
            addCriterion("a.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Integer value) {
            addCriterion("a.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Integer value) {
            addCriterion("a.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Integer value) {
            addCriterion("a.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Integer value) {
            addCriterion("a.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Integer> values) {
            addCriterion("a.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Integer> values) {
            addCriterion("a.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Integer value1, Integer value2) {
            addCriterion("a.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("a.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIsNull() {
            addCriterion("a.activation_time is null");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIsNotNull() {
            addCriterion("a.activation_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivationTimeEqualTo(Integer value) {
            addCriterion("a.activation_time =", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotEqualTo(Integer value) {
            addCriterion("a.activation_time <>", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeGreaterThan(Integer value) {
            addCriterion("a.activation_time >", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.activation_time >=", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeLessThan(Integer value) {
            addCriterion("a.activation_time <", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeLessThanOrEqualTo(Integer value) {
            addCriterion("a.activation_time <=", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIn(List<Integer> values) {
            addCriterion("a.activation_time in", values, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotIn(List<Integer> values) {
            addCriterion("a.activation_time not in", values, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeBetween(Integer value1, Integer value2) {
            addCriterion("a.activation_time between", value1, value2, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("a.activation_time not between", value1, value2, "activationTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("a.end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("a.end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Integer value) {
            addCriterion("a.end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Integer value) {
            addCriterion("a.end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Integer value) {
            addCriterion("a.end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Integer value) {
            addCriterion("a.end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("a.end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Integer> values) {
            addCriterion("a.end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Integer> values) {
            addCriterion("a.end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("a.end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("a.end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeIsNull() {
            addCriterion("a.renewal_time is null");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeIsNotNull() {
            addCriterion("a.renewal_time is not null");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeEqualTo(Integer value) {
            addCriterion("a.renewal_time =", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeNotEqualTo(Integer value) {
            addCriterion("a.renewal_time <>", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeGreaterThan(Integer value) {
            addCriterion("a.renewal_time >", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.renewal_time >=", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeLessThan(Integer value) {
            addCriterion("a.renewal_time <", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeLessThanOrEqualTo(Integer value) {
            addCriterion("a.renewal_time <=", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeIn(List<Integer> values) {
            addCriterion("a.renewal_time in", values, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeNotIn(List<Integer> values) {
            addCriterion("a.renewal_time not in", values, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeBetween(Integer value1, Integer value2) {
            addCriterion("a.renewal_time between", value1, value2, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("a.renewal_time not between", value1, value2, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("a.remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("a.remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("a.remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("a.remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("a.remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("a.remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("a.remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("a.remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("a.remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("a.remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("a.remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("a.remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("a.remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("a.remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNull() {
            addCriterion("a.factory_id is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNotNull() {
            addCriterion("a.factory_id is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdEqualTo(Integer value) {
            addCriterion("a.factory_id =", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotEqualTo(Integer value) {
            addCriterion("a.factory_id <>", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThan(Integer value) {
            addCriterion("a.factory_id >", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.factory_id >=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThan(Integer value) {
            addCriterion("a.factory_id <", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("a.factory_id <=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIn(List<Integer> values) {
            addCriterion("a.factory_id in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotIn(List<Integer> values) {
            addCriterion("a.factory_id not in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdBetween(Integer value1, Integer value2) {
            addCriterion("a.factory_id between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("a.factory_id not between", value1, value2, "factoryId");
            return (Criteria) this;
        }
        
        public Criteria andLastLoginTimeIsNull() {
            addCriterion("a.last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("a.last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Integer value) {
            addCriterion("a.last_login_time =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Integer value) {
            addCriterion("a.last_login_time <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Integer value) {
            addCriterion("a.last_login_time >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("a.last_login_time >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Integer value) {
            addCriterion("a.last_login_time <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Integer value) {
            addCriterion("a.last_login_time <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Integer> values) {
            addCriterion("a.last_login_time in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Integer> values) {
            addCriterion("a.last_login_time not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Integer value1, Integer value2) {
            addCriterion("a.last_login_time between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("a.last_login_time not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("a.version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("a.version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("a.version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("a.version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("a.version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("a.version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("a.version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("a.version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("a.version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("a.version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("a.version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("a.version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("a.version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("a.version not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
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
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
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
}