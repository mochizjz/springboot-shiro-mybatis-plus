/******************************************************************************
* CREATETIME : 2020年7月18日 下午1:08:20
******************************************************************************/
package ins.webeye.project.eye.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <pre></pre>
 * @author ★LiuPing
 */
@ApiModel(description = "订单信息回写接口的请求信息")
@Data
public class OrderInfoVo {

	@NotNull(message = "消费方系统编码不能为空")
	@ApiModelProperty(value = "消费方系统代码")
	private String systemCode;

	@NotNull(message = "消费方ID不能为空")
	@ApiModelProperty(value = "消费方ID", required = true)
	private String consumerID; // 消费方ID
	
	@NotNull(message = "消费方密码不能为空")
	@ApiModelProperty(value = "消费方密码", required = true)
	private String consumerPWD; // 消费方密码

	@NotNull(message = "订单号不能为空")
	@ApiModelProperty(value = "订单号", required = true)
	private String orderId;// 0-未支付，1-已支付成功
	
	@NotNull(message = "产品代码不能为空")
	@ApiModelProperty(value = "产品代码")
	private String productCode;

	@NotNull(message = "支付状态不能为空")
	@ApiModelProperty(value = "支付状态", required = true)
	private String payStatus;

	@NotNull(message = "保单号不能为空")
	@ApiModelProperty(value = "保单号")
	private String policyNo;
	
	@NotNull(message = "投保人姓名不能为空")
	@ApiModelProperty(value = "投保人姓名")
	private String policyName;
	
	@NotNull(message = "投保人证件号不能为空")
	@ApiModelProperty(value = "投保人证件号")
	private String policyIdcard;
	
	//@NotNull(message = "token不能为空")
	@ApiModelProperty(value = "token")
	private String token;

	
}
