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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(Integer value) {
            addCriterion("organization_id =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(Integer value) {
            addCriterion("organization_id <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(Integer value) {
            addCriterion("organization_id >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("organization_id >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(Integer value) {
            addCriterion("organization_id <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(Integer value) {
            addCriterion("organization_id <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<Integer> values) {
            addCriterion("organization_id in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<Integer> values) {
            addCriterion("organization_id not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(Integer value1, Integer value2) {
            addCriterion("organization_id between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("organization_id not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andMacIdIsNull() {
            addCriterion("mac_id is null");
            return (Criteria) this;
        }

        public Criteria andMacIdIsNotNull() {
            addCriterion("mac_id is not null");
            return (Criteria) this;
        }

        public Criteria andMacIdEqualTo(String value) {
            addCriterion("mac_id =", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotEqualTo(String value) {
            addCriterion("mac_id <>", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdGreaterThan(String value) {
            addCriterion("mac_id >", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdGreaterThanOrEqualTo(String value) {
            addCriterion("mac_id >=", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLessThan(String value) {
            addCriterion("mac_id <", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLessThanOrEqualTo(String value) {
            addCriterion("mac_id <=", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdLike(String value) {
            addCriterion("mac_id like", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotLike(String value) {
            addCriterion("mac_id not like", value, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdIn(List<String> values) {
            addCriterion("mac_id in", values, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotIn(List<String> values) {
            addCriterion("mac_id not in", values, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdBetween(String value1, String value2) {
            addCriterion("mac_id between", value1, value2, "macId");
            return (Criteria) this;
        }

        public Criteria andMacIdNotBetween(String value1, String value2) {
            addCriterion("mac_id not between", value1, value2, "macId");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberIsNull() {
            addCriterion("equipment_number is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberIsNotNull() {
            addCriterion("equipment_number is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberEqualTo(String value) {
            addCriterion("equipment_number =", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotEqualTo(String value) {
            addCriterion("equipment_number <>", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberGreaterThan(String value) {
            addCriterion("equipment_number >", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_number >=", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberLessThan(String value) {
            addCriterion("equipment_number <", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberLessThanOrEqualTo(String value) {
            addCriterion("equipment_number <=", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberLike(String value) {
            addCriterion("equipment_number like", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotLike(String value) {
            addCriterion("equipment_number not like", value, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberIn(List<String> values) {
            addCriterion("equipment_number in", values, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotIn(List<String> values) {
            addCriterion("equipment_number not in", values, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberBetween(String value1, String value2) {
            addCriterion("equipment_number between", value1, value2, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentNumberNotBetween(String value1, String value2) {
            addCriterion("equipment_number not between", value1, value2, "equipmentNumber");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdIsNull() {
            addCriterion("equipment_pwd is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdIsNotNull() {
            addCriterion("equipment_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdEqualTo(String value) {
            addCriterion("equipment_pwd =", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotEqualTo(String value) {
            addCriterion("equipment_pwd <>", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdGreaterThan(String value) {
            addCriterion("equipment_pwd >", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_pwd >=", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdLessThan(String value) {
            addCriterion("equipment_pwd <", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdLessThanOrEqualTo(String value) {
            addCriterion("equipment_pwd <=", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdLike(String value) {
            addCriterion("equipment_pwd like", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotLike(String value) {
            addCriterion("equipment_pwd not like", value, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdIn(List<String> values) {
            addCriterion("equipment_pwd in", values, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotIn(List<String> values) {
            addCriterion("equipment_pwd not in", values, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdBetween(String value1, String value2) {
            addCriterion("equipment_pwd between", value1, value2, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPwdNotBetween(String value1, String value2) {
            addCriterion("equipment_pwd not between", value1, value2, "equipmentPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdIsNull() {
            addCriterion("equipment_plain_pwd is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdIsNotNull() {
            addCriterion("equipment_plain_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdEqualTo(String value) {
            addCriterion("equipment_plain_pwd =", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotEqualTo(String value) {
            addCriterion("equipment_plain_pwd <>", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdGreaterThan(String value) {
            addCriterion("equipment_plain_pwd >", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_plain_pwd >=", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdLessThan(String value) {
            addCriterion("equipment_plain_pwd <", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdLessThanOrEqualTo(String value) {
            addCriterion("equipment_plain_pwd <=", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdLike(String value) {
            addCriterion("equipment_plain_pwd like", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotLike(String value) {
            addCriterion("equipment_plain_pwd not like", value, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdIn(List<String> values) {
            addCriterion("equipment_plain_pwd in", values, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotIn(List<String> values) {
            addCriterion("equipment_plain_pwd not in", values, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdBetween(String value1, String value2) {
            addCriterion("equipment_plain_pwd between", value1, value2, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentPlainPwdNotBetween(String value1, String value2) {
            addCriterion("equipment_plain_pwd not between", value1, value2, "equipmentPlainPwd");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeIsNull() {
            addCriterion("equipment_type is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeIsNotNull() {
            addCriterion("equipment_type is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeEqualTo(Boolean value) {
            addCriterion("equipment_type =", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeNotEqualTo(Boolean value) {
            addCriterion("equipment_type <>", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeGreaterThan(Boolean value) {
            addCriterion("equipment_type >", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("equipment_type >=", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeLessThan(Boolean value) {
            addCriterion("equipment_type <", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("equipment_type <=", value, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeIn(List<Boolean> values) {
            addCriterion("equipment_type in", values, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeNotIn(List<Boolean> values) {
            addCriterion("equipment_type not in", values, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("equipment_type between", value1, value2, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andEquipmentTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("equipment_type not between", value1, value2, "equipmentType");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeIsNull() {
            addCriterion("renewal_fee is null");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeIsNotNull() {
            addCriterion("renewal_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeEqualTo(Integer value) {
            addCriterion("renewal_fee =", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeNotEqualTo(Integer value) {
            addCriterion("renewal_fee <>", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeGreaterThan(Integer value) {
            addCriterion("renewal_fee >", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("renewal_fee >=", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeLessThan(Integer value) {
            addCriterion("renewal_fee <", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeLessThanOrEqualTo(Integer value) {
            addCriterion("renewal_fee <=", value, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeIn(List<Integer> values) {
            addCriterion("renewal_fee in", values, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeNotIn(List<Integer> values) {
            addCriterion("renewal_fee not in", values, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeBetween(Integer value1, Integer value2) {
            addCriterion("renewal_fee between", value1, value2, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andRenewalFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("renewal_fee not between", value1, value2, "renewalFee");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Integer value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Integer value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Integer value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Integer value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Integer value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Integer> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Integer> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Integer value1, Integer value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIsNull() {
            addCriterion("activation_time is null");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIsNotNull() {
            addCriterion("activation_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivationTimeEqualTo(Integer value) {
            addCriterion("activation_time =", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotEqualTo(Integer value) {
            addCriterion("activation_time <>", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeGreaterThan(Integer value) {
            addCriterion("activation_time >", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("activation_time >=", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeLessThan(Integer value) {
            addCriterion("activation_time <", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeLessThanOrEqualTo(Integer value) {
            addCriterion("activation_time <=", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIn(List<Integer> values) {
            addCriterion("activation_time in", values, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotIn(List<Integer> values) {
            addCriterion("activation_time not in", values, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeBetween(Integer value1, Integer value2) {
            addCriterion("activation_time between", value1, value2, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("activation_time not between", value1, value2, "activationTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Integer value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Integer value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Integer value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Integer value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Integer value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Integer> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Integer> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Integer value1, Integer value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeIsNull() {
            addCriterion("renewal_time is null");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeIsNotNull() {
            addCriterion("renewal_time is not null");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeEqualTo(Integer value) {
            addCriterion("renewal_time =", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeNotEqualTo(Integer value) {
            addCriterion("renewal_time <>", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeGreaterThan(Integer value) {
            addCriterion("renewal_time >", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("renewal_time >=", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeLessThan(Integer value) {
            addCriterion("renewal_time <", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeLessThanOrEqualTo(Integer value) {
            addCriterion("renewal_time <=", value, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeIn(List<Integer> values) {
            addCriterion("renewal_time in", values, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeNotIn(List<Integer> values) {
            addCriterion("renewal_time not in", values, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeBetween(Integer value1, Integer value2) {
            addCriterion("renewal_time between", value1, value2, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRenewalTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("renewal_time not between", value1, value2, "renewalTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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