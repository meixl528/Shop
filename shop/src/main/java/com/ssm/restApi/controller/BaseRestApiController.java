package com.ssm.restApi.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @name BaseRestController
 * @description 处理RestAPI的基础控制类(*和{} 表示允许所有)
 * 
 * <br>
 * <b>List of allowed origins, e.g. {@code "http://192.168.10.25:8081/system"}.
 * <p>These values are placed in the {@code Access-Control-Allow-Origin}
 * header of both the pre-flight response and the actual response.
 * {@code "*"} means that all origins are allowed.
 * <p>If undefined, all origins are allowed.
 * </b>
 */
@RestController
@CrossOrigin
public class BaseRestApiController {

	@Autowired
    private Validator validator;

	/**
	 * 返回状态代码
	 */
	protected final String CODE = "code";
	/**
	 * 返回消息
	 */
	protected final String MSG = "msg";
	/**
	 * 返回总条数
	 */
	protected final String TOTAL = "total";
	/**
	 * 返回内容
	 */
	protected final String CONTENT = "content";
	/**
	 * 成功标识
	 */
	protected final String SUCCESS = "S";
	/**
	 * 失败标识
	 */
	protected final String ERROR = "E";
	/**
	 * 没有操作权限的标识
	 */
	protected final String OPERATE_NO_AUTH_MSG = "wg.fnd.no_operate_auth";

	@Autowired
	protected ObjectMapper objectMapper;

	/**
	 * Redis描述名称
	 */
	private final String REDIS_PROMPT = "prompt";

	private final String REDIS_CODE = CODE;

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}


	/**
	 * 验证请求参数Javabean的属性值空置验证,对 @NotNull、@NotEmpty都合适
	 * @param obj
	 * @param message
	 * @return
	 */
	protected <T> boolean validateRequestParam(T obj, StringBuilder message) {
		ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = vFactory.getValidator();
		Set<ConstraintViolation<T>> set = validator.validate(obj);
		if (set.size() > 0) {
			for (ConstraintViolation<T> val : set) {
				message.append(val.getMessage()+";");
			}
			return false;
		}
		return true;
	}

	/**
	 * 从缓存中通过token获取user信息
	 *
	 * @param token
	 * @return
	 */
	/*protected UmUser getUserByToken(String token) {
		if (StringUtils.isNotBlank(token)) {
			String userId = umUserService.getUserToken(token);
			UmUser umUser = null;
			if (StringUtils.isNotBlank(userId)) {
				umUser = umUserService.queryRedisUser(Long.parseLong(userId));
			}
			return umUser;
		}
		return null;
	}*/
	
	/**
	 * 校验用户与能操作交易主体的关系
	 * @param user
	 * @return
	 */
	/*protected boolean checkUserParty(Long fuserId,Long tradePartyId){
		return umUserService.checkUserParty(fuserId, tradePartyId);
	}*/

	/**
	 * 从缓存中获取用户信息
	 *
	 * @param umUser
	 * @return
	 */
	/*protected UmUser getUserByCache(UmUser umUser) {
		UmUser userCache = null;
		String userId = null;
		if (StringUtils.isNotBlank(umUser.getUserName())) {
			Cache<String> umUserIdCacheList = cacheManager.getCache(IUmUserService.CACHE_UM_USER_NAME);
			userId = umUserIdCacheList.getValue(umUser.getUserName());
		} else if (StringUtils.isNotBlank(umUser.getPhone())) {
			Cache<String> umUserIdCacheList = cacheManager.getCache(IUmUserService.CACHE_UM_USER_PHONE);
			userId = umUserIdCacheList.getValue(umUser.getPhone());
		} else if (StringUtils.isNotBlank(umUser.getEmail())) {
			Cache<String> umUserIdCacheList = cacheManager.getCache(IUmUserService.CACHE_UM_USER_EMAIL);
			userId = umUserIdCacheList.getValue(umUser.getEmail());
		}

		if (userId != null) {
			userCache = umUserService.queryRedisUser(Long.parseLong(userId));
		}

		return userCache;
	}*/

	/**
	 * 用户验证
	 *
	 * @param lang
	 * @return
	 */
	/*protected void userLoginError(Map<String, Object> resultMap, String lang) {
		resultMap.put(CODE, ERROR);
		resultMap.put(MSG,this.getMessage(IUmRestApiService.USER_NOT_LOGIN, lang));
	}*/
	
	/**
	 * 用户数据权限验证
	 *
	 * @param lang
	 * @return
	 */
	/*protected void userDataAuthError(Map<String, Object> resultMap, String lang) {
		resultMap.put(CODE, ERROR);
		resultMap.put(MSG,this.getMessage(IUmRestApiService.USER_NOT_LOGIN, lang));
	}*/

	/**
	 * 校验邮箱是否正确
	 *
	 * @param resultMap
	 * @param captchaCode
	 * @param flag
	 * @param checkEmailCaptcha
	 * @param lang
	 * @return
	 */
	/*protected boolean checkEmailCaptcha(Map<String, Object> resultMap,
			String captchaCode, String emailCaptchaCode, String lang) {
		if ((StringUtils.isEmpty(emailCaptchaCode) || !captchaManager.checkCaptcha(captchaCode + "email", emailCaptchaCode))) {
			resultMap.put(CODE, ERROR);
			resultMap.put(MSG, this.getMessage(IUmRestApiService.EMAIL_VERIFICATION_CODE_ERROR, lang));
			return false;
		}
		return true;
	}*/

	/**
	 * 校验手机是否正确
	 *
	 * @param resultMap
	 * @param captchaCode
	 * @param flag
	 * @param phoneCaptchaCode
	 * @param lang
	 * @return
	 */
	/*protected boolean checkPhoneCaptcha(Map<String, Object> resultMap,
			String captchaCode, String phoneCaptchaCode, String lang) {
		if ((StringUtils.isEmpty(phoneCaptchaCode) || !captchaManager.checkCaptcha(captchaCode + "phone", phoneCaptchaCode))) {
			resultMap.put(CODE, ERROR);
			resultMap.put(MSG, this.getMessage(IUmRestApiService.PHONE_VERIFICATION_CODE_ERROR, lang));
			return false;
		}
		return true;
	}*/

	/**
	 * 校验防篡改token
	 *
	 * @param dto
	 * @param resultMap
	 * @param lang
	 */
	/*protected boolean checkDefaceToken(BaseDTO dto,
			Map<String, Object> resultMap, String lang) {
		boolean flag = false;
		if (dto != null || !defeatDefaceHelper.checkDefaceToken(dto)) {
			resultMap.put(CODE, ERROR);
			resultMap.put(MSG, this.getMessage(IFndRestApiService.CERTIFY_DATA_ERROR, lang));
			flag = true;
		}
		return flag;
	}*/
	
	/**
	 * 获取没有操作权限的消息提示
	 * @param lang
	 * @return
	 */
	/*protected String getNoOperateAuthMsg(String lang){
		return this.getMessage(OPERATE_NO_AUTH_MSG, lang);
	}*/

}
