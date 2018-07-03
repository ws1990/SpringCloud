package com.ws.springcloud.auth.server.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResourceExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andUriIsNull() {
            addCriterion("uri is null");
            return (Criteria) this;
        }

        public Criteria andUriIsNotNull() {
            addCriterion("uri is not null");
            return (Criteria) this;
        }

        public Criteria andUriEqualTo(String value) {
            addCriterion("uri =", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriNotEqualTo(String value) {
            addCriterion("uri <>", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriGreaterThan(String value) {
            addCriterion("uri >", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriGreaterThanOrEqualTo(String value) {
            addCriterion("uri >=", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriLessThan(String value) {
            addCriterion("uri <", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriLessThanOrEqualTo(String value) {
            addCriterion("uri <=", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriLike(String value) {
            addCriterion("uri like", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriNotLike(String value) {
            addCriterion("uri not like", value, "uri");
            return (Criteria) this;
        }

        public Criteria andUriIn(List<String> values) {
            addCriterion("uri in", values, "uri");
            return (Criteria) this;
        }

        public Criteria andUriNotIn(List<String> values) {
            addCriterion("uri not in", values, "uri");
            return (Criteria) this;
        }

        public Criteria andUriBetween(String value1, String value2) {
            addCriterion("uri between", value1, value2, "uri");
            return (Criteria) this;
        }

        public Criteria andUriNotBetween(String value1, String value2) {
            addCriterion("uri not between", value1, value2, "uri");
            return (Criteria) this;
        }

        public Criteria andApiIsNull() {
            addCriterion("api is null");
            return (Criteria) this;
        }

        public Criteria andApiIsNotNull() {
            addCriterion("api is not null");
            return (Criteria) this;
        }

        public Criteria andApiEqualTo(String value) {
            addCriterion("api =", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiNotEqualTo(String value) {
            addCriterion("api <>", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiGreaterThan(String value) {
            addCriterion("api >", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiGreaterThanOrEqualTo(String value) {
            addCriterion("api >=", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiLessThan(String value) {
            addCriterion("api <", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiLessThanOrEqualTo(String value) {
            addCriterion("api <=", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiLike(String value) {
            addCriterion("api like", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiNotLike(String value) {
            addCriterion("api not like", value, "api");
            return (Criteria) this;
        }

        public Criteria andApiIn(List<String> values) {
            addCriterion("api in", values, "api");
            return (Criteria) this;
        }

        public Criteria andApiNotIn(List<String> values) {
            addCriterion("api not in", values, "api");
            return (Criteria) this;
        }

        public Criteria andApiBetween(String value1, String value2) {
            addCriterion("api between", value1, value2, "api");
            return (Criteria) this;
        }

        public Criteria andApiNotBetween(String value1, String value2) {
            addCriterion("api not between", value1, value2, "api");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("depth is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("depth is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(Integer value) {
            addCriterion("depth =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(Integer value) {
            addCriterion("depth <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(Integer value) {
            addCriterion("depth >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(Integer value) {
            addCriterion("depth >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(Integer value) {
            addCriterion("depth <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(Integer value) {
            addCriterion("depth <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<Integer> values) {
            addCriterion("depth in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<Integer> values) {
            addCriterion("depth not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(Integer value1, Integer value2) {
            addCriterion("depth between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(Integer value1, Integer value2) {
            addCriterion("depth not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNull() {
            addCriterion("sequence is null");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNotNull() {
            addCriterion("sequence is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceEqualTo(Integer value) {
            addCriterion("sequence =", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotEqualTo(Integer value) {
            addCriterion("sequence <>", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThan(Integer value) {
            addCriterion("sequence >", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequence >=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThan(Integer value) {
            addCriterion("sequence <", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThanOrEqualTo(Integer value) {
            addCriterion("sequence <=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceIn(List<Integer> values) {
            addCriterion("sequence in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotIn(List<Integer> values) {
            addCriterion("sequence not in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceBetween(Integer value1, Integer value2) {
            addCriterion("sequence between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("sequence not between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andIdsIsNull() {
            addCriterion("ids is null");
            return (Criteria) this;
        }

        public Criteria andIdsIsNotNull() {
            addCriterion("ids is not null");
            return (Criteria) this;
        }

        public Criteria andIdsEqualTo(String value) {
            addCriterion("ids =", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsNotEqualTo(String value) {
            addCriterion("ids <>", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsGreaterThan(String value) {
            addCriterion("ids >", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsGreaterThanOrEqualTo(String value) {
            addCriterion("ids >=", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsLessThan(String value) {
            addCriterion("ids <", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsLessThanOrEqualTo(String value) {
            addCriterion("ids <=", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsLike(String value) {
            addCriterion("ids like", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsNotLike(String value) {
            addCriterion("ids not like", value, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsIn(List<String> values) {
            addCriterion("ids in", values, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsNotIn(List<String> values) {
            addCriterion("ids not in", values, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsBetween(String value1, String value2) {
            addCriterion("ids between", value1, value2, "ids");
            return (Criteria) this;
        }

        public Criteria andIdsNotBetween(String value1, String value2) {
            addCriterion("ids not between", value1, value2, "ids");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsLockIsNull() {
            addCriterion("is_lock is null");
            return (Criteria) this;
        }

        public Criteria andIsLockIsNotNull() {
            addCriterion("is_lock is not null");
            return (Criteria) this;
        }

        public Criteria andIsLockEqualTo(Boolean value) {
            addCriterion("is_lock =", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotEqualTo(Boolean value) {
            addCriterion("is_lock <>", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockGreaterThan(Boolean value) {
            addCriterion("is_lock >", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_lock >=", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLessThan(Boolean value) {
            addCriterion("is_lock <", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLessThanOrEqualTo(Boolean value) {
            addCriterion("is_lock <=", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockIn(List<Boolean> values) {
            addCriterion("is_lock in", values, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotIn(List<Boolean> values) {
            addCriterion("is_lock not in", values, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockBetween(Boolean value1, Boolean value2) {
            addCriterion("is_lock between", value1, value2, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_lock not between", value1, value2, "isLock");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("creator_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("creator_id =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("creator_id <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("creator_id >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("creator_id >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("creator_id <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("creator_id <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("creator_id like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("creator_id not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("creator_id in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("creator_id not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("creator_id between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("creator_id not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIsNull() {
            addCriterion("update_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIsNotNull() {
            addCriterion("update_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateIdEqualTo(String value) {
            addCriterion("update_id =", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotEqualTo(String value) {
            addCriterion("update_id <>", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdGreaterThan(String value) {
            addCriterion("update_id >", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdGreaterThanOrEqualTo(String value) {
            addCriterion("update_id >=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLessThan(String value) {
            addCriterion("update_id <", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLessThanOrEqualTo(String value) {
            addCriterion("update_id <=", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdLike(String value) {
            addCriterion("update_id like", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotLike(String value) {
            addCriterion("update_id not like", value, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdIn(List<String> values) {
            addCriterion("update_id in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotIn(List<String> values) {
            addCriterion("update_id not in", values, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdBetween(String value1, String value2) {
            addCriterion("update_id between", value1, value2, "updateId");
            return (Criteria) this;
        }

        public Criteria andUpdateIdNotBetween(String value1, String value2) {
            addCriterion("update_id not between", value1, value2, "updateId");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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