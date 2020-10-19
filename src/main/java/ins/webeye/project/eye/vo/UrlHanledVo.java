package ins.webeye.project.eye.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * url处理vo
 * <pre></pre>
 * @author ★XiaoQuan
 * @CreateTime 2020年7月22日
 */
@Data
public class UrlHanledVo {

	/*@NotNull(message = "pageId不能为空")
	@ApiModelProperty(value = "当前页面id", required = true)*/
	private String pageId;
	
	@NotNull(message = "token不能为空")
	@ApiModelProperty(value = "token", required = true)
	private String token;

	@NotNull(message = "节点代码不能为空")
	@ApiModelProperty(value = "节点代码", required = true)
	private String nodeCode;
	
	@NotNull(message = "url地址不能为空")
	@ApiModelProperty(value = "url地址", required = true)
	private String urlStrings;
	
	@NotNull(message = "产品代码不能为空")
	@ApiModelProperty(value = "产品代码", required = true)
	private String productCode;
	
	private String userId;//操作人员id
	 
	private String productName;
}
