package com.lan.website.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andMailIsNull() {
            addCriterion("mail is null");
            return (Criteria) this;
        }

        public Criteria andMailIsNotNull() {
            addCriterion("mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailEqualTo(String value) {
            addCriterion("mail =", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotEqualTo(String value) {
            addCriterion("mail <>", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThan(String value) {
            addCriterion("mail >", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail >=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThan(String value) {
            addCriterion("mail <", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThanOrEqualTo(String value) {
            addCriterion("mail <=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLike(String value) {
            addCriterion("mail like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotLike(String value) {
            addCriterion("mail not like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailIn(List<String> values) {
            addCriterion("mail in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotIn(List<String> values) {
            addCriterion("mail not in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailBetween(String value1, String value2) {
            addCriterion("mail between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotBetween(String value1, String value2) {
            addCriterion("mail not between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andVxIsNull() {
            addCriterion("vx is null");
            return (Criteria) this;
        }

        public Criteria andVxIsNotNull() {
            addCriterion("vx is not null");
            return (Criteria) this;
        }

        public Criteria andVxEqualTo(String value) {
            addCriterion("vx =", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxNotEqualTo(String value) {
            addCriterion("vx <>", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxGreaterThan(String value) {
            addCriterion("vx >", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxGreaterThanOrEqualTo(String value) {
            addCriterion("vx >=", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxLessThan(String value) {
            addCriterion("vx <", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxLessThanOrEqualTo(String value) {
            addCriterion("vx <=", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxLike(String value) {
            addCriterion("vx like", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxNotLike(String value) {
            addCriterion("vx not like", value, "vx");
            return (Criteria) this;
        }

        public Criteria andVxIn(List<String> values) {
            addCriterion("vx in", values, "vx");
            return (Criteria) this;
        }

        public Criteria andVxNotIn(List<String> values) {
            addCriterion("vx not in", values, "vx");
            return (Criteria) this;
        }

        public Criteria andVxBetween(String value1, String value2) {
            addCriterion("vx between", value1, value2, "vx");
            return (Criteria) this;
        }

        public Criteria andVxNotBetween(String value1, String value2) {
            addCriterion("vx not between", value1, value2, "vx");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andMissionIsNull() {
            addCriterion("mission is null");
            return (Criteria) this;
        }

        public Criteria andMissionIsNotNull() {
            addCriterion("mission is not null");
            return (Criteria) this;
        }

        public Criteria andMissionEqualTo(String value) {
            addCriterion("mission =", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotEqualTo(String value) {
            addCriterion("mission <>", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionGreaterThan(String value) {
            addCriterion("mission >", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionGreaterThanOrEqualTo(String value) {
            addCriterion("mission >=", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionLessThan(String value) {
            addCriterion("mission <", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionLessThanOrEqualTo(String value) {
            addCriterion("mission <=", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionLike(String value) {
            addCriterion("mission like", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotLike(String value) {
            addCriterion("mission not like", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionIn(List<String> values) {
            addCriterion("mission in", values, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotIn(List<String> values) {
            addCriterion("mission not in", values, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionBetween(String value1, String value2) {
            addCriterion("mission between", value1, value2, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotBetween(String value1, String value2) {
            addCriterion("mission not between", value1, value2, "mission");
            return (Criteria) this;
        }

        public Criteria andBuycaseIsNull() {
            addCriterion("buycase is null");
            return (Criteria) this;
        }

        public Criteria andBuycaseIsNotNull() {
            addCriterion("buycase is not null");
            return (Criteria) this;
        }

        public Criteria andBuycaseEqualTo(String value) {
            addCriterion("buycase =", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseNotEqualTo(String value) {
            addCriterion("buycase <>", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseGreaterThan(String value) {
            addCriterion("buycase >", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseGreaterThanOrEqualTo(String value) {
            addCriterion("buycase >=", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseLessThan(String value) {
            addCriterion("buycase <", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseLessThanOrEqualTo(String value) {
            addCriterion("buycase <=", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseLike(String value) {
            addCriterion("buycase like", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseNotLike(String value) {
            addCriterion("buycase not like", value, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseIn(List<String> values) {
            addCriterion("buycase in", values, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseNotIn(List<String> values) {
            addCriterion("buycase not in", values, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseBetween(String value1, String value2) {
            addCriterion("buycase between", value1, value2, "buycase");
            return (Criteria) this;
        }

        public Criteria andBuycaseNotBetween(String value1, String value2) {
            addCriterion("buycase not between", value1, value2, "buycase");
            return (Criteria) this;
        }

        public Criteria andMycaseIsNull() {
            addCriterion("mycase is null");
            return (Criteria) this;
        }

        public Criteria andMycaseIsNotNull() {
            addCriterion("mycase is not null");
            return (Criteria) this;
        }

        public Criteria andMycaseEqualTo(String value) {
            addCriterion("mycase =", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseNotEqualTo(String value) {
            addCriterion("mycase <>", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseGreaterThan(String value) {
            addCriterion("mycase >", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseGreaterThanOrEqualTo(String value) {
            addCriterion("mycase >=", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseLessThan(String value) {
            addCriterion("mycase <", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseLessThanOrEqualTo(String value) {
            addCriterion("mycase <=", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseLike(String value) {
            addCriterion("mycase like", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseNotLike(String value) {
            addCriterion("mycase not like", value, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseIn(List<String> values) {
            addCriterion("mycase in", values, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseNotIn(List<String> values) {
            addCriterion("mycase not in", values, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseBetween(String value1, String value2) {
            addCriterion("mycase between", value1, value2, "mycase");
            return (Criteria) this;
        }

        public Criteria andMycaseNotBetween(String value1, String value2) {
            addCriterion("mycase not between", value1, value2, "mycase");
            return (Criteria) this;
        }

        public Criteria andCollectIsNull() {
            addCriterion("collect is null");
            return (Criteria) this;
        }

        public Criteria andCollectIsNotNull() {
            addCriterion("collect is not null");
            return (Criteria) this;
        }

        public Criteria andCollectEqualTo(String value) {
            addCriterion("collect =", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotEqualTo(String value) {
            addCriterion("collect <>", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectGreaterThan(String value) {
            addCriterion("collect >", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectGreaterThanOrEqualTo(String value) {
            addCriterion("collect >=", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectLessThan(String value) {
            addCriterion("collect <", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectLessThanOrEqualTo(String value) {
            addCriterion("collect <=", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectLike(String value) {
            addCriterion("collect like", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotLike(String value) {
            addCriterion("collect not like", value, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectIn(List<String> values) {
            addCriterion("collect in", values, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotIn(List<String> values) {
            addCriterion("collect not in", values, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectBetween(String value1, String value2) {
            addCriterion("collect between", value1, value2, "collect");
            return (Criteria) this;
        }

        public Criteria andCollectNotBetween(String value1, String value2) {
            addCriterion("collect not between", value1, value2, "collect");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andVipIsNull() {
            addCriterion("vip is null");
            return (Criteria) this;
        }

        public Criteria andVipIsNotNull() {
            addCriterion("vip is not null");
            return (Criteria) this;
        }

        public Criteria andVipEqualTo(Date value) {
            addCriterion("vip =", value, "vip");
            return (Criteria) this;
        }

        public Criteria andVipNotEqualTo(Date value) {
            addCriterion("vip <>", value, "vip");
            return (Criteria) this;
        }

        public Criteria andVipGreaterThan(Date value) {
            addCriterion("vip >", value, "vip");
            return (Criteria) this;
        }

        public Criteria andVipGreaterThanOrEqualTo(Date value) {
            addCriterion("vip >=", value, "vip");
            return (Criteria) this;
        }

        public Criteria andVipLessThan(Date value) {
            addCriterion("vip <", value, "vip");
            return (Criteria) this;
        }

        public Criteria andVipLessThanOrEqualTo(Date value) {
            addCriterion("vip <=", value, "vip");
            return (Criteria) this;
        }

        public Criteria andVipIn(List<Date> values) {
            addCriterion("vip in", values, "vip");
            return (Criteria) this;
        }

        public Criteria andVipNotIn(List<Date> values) {
            addCriterion("vip not in", values, "vip");
            return (Criteria) this;
        }

        public Criteria andVipBetween(Date value1, Date value2) {
            addCriterion("vip between", value1, value2, "vip");
            return (Criteria) this;
        }

        public Criteria andVipNotBetween(Date value1, Date value2) {
            addCriterion("vip not between", value1, value2, "vip");
            return (Criteria) this;
        }

        public Criteria andFristtimeIsNull() {
            addCriterion("fristtime is null");
            return (Criteria) this;
        }

        public Criteria andFristtimeIsNotNull() {
            addCriterion("fristtime is not null");
            return (Criteria) this;
        }

        public Criteria andFristtimeEqualTo(Date value) {
            addCriterion("fristtime =", value, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeNotEqualTo(Date value) {
            addCriterion("fristtime <>", value, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeGreaterThan(Date value) {
            addCriterion("fristtime >", value, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fristtime >=", value, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeLessThan(Date value) {
            addCriterion("fristtime <", value, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeLessThanOrEqualTo(Date value) {
            addCriterion("fristtime <=", value, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeIn(List<Date> values) {
            addCriterion("fristtime in", values, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeNotIn(List<Date> values) {
            addCriterion("fristtime not in", values, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeBetween(Date value1, Date value2) {
            addCriterion("fristtime between", value1, value2, "fristtime");
            return (Criteria) this;
        }

        public Criteria andFristtimeNotBetween(Date value1, Date value2) {
            addCriterion("fristtime not between", value1, value2, "fristtime");
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