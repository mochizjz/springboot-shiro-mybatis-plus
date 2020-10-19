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
@ApiModel(description = "获取录制凭证（Token）请求实体")
@Data
public class TokenRequestVo {

	@NotNull(message = "消费方系统编码")
	@ApiModelProperty(value = "消费方系统代码", required = true)
	private String systemCode;

	@ApiModelProperty(value = "消费方系统名称（停用）")
	private String systemName;

	@NotNull(message = "消费方ID不能为空")
	@ApiModelProperty(value = "消费方ID", required = true)
	private String consumerID; // 消费方ID

	@NotNull(message = "消费方密码不能为空")
	@ApiModelProperty(value = "消费方密码", required = true)
	private String consumerPWD; // 消费方密码

	@NotNull(message = "产品代码不能为空")
	@ApiModelProperty(value = "产品代码", required = true)
	private String productCode;

	@NotNull(message = "产品名称不能为空")
	@ApiModelProperty(value = "产品名称", required = true)
	private String productName;

	@ApiModelProperty("当前操作人员id")
	private String userId;

	@ApiModelProperty("当前操作的订单（修改订单时用）")
	private String orderId;
}
