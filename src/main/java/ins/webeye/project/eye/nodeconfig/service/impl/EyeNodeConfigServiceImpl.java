package ins.webeye.project.eye.nodeconfig.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ins.webeye.common.constant.Constants;
import ins.webeye.common.constant.RedisConstants;
import ins.webeye.common.utils.DateUtils;
import ins.webeye.common.utils.RedisUtil;
import ins.webeye.common.utils.security.ShiroUtils;
import ins.webeye.common.utils.text.Convert;
import ins.webeye.project.eye.nodeconfig.domain.EyeNodeConfig;
import ins.webeye.project.eye.nodeconfig.mapper.EyeNodeConfigMapper;
import ins.webeye.project.eye.nodeconfig.service.IEyeNodeConfigService;

/**
 * 节点配置Service业务层处理
 * 
 * @author xiaoquan
 * @date 2020-07-16
 */
@Service
public class EyeNodeConfigServiceImpl implements IEyeNodeConfigService 
{
    @Autowired
    private EyeNodeConfigMapper eyeNodeConfigMapper;

	@Autowired
	private RedisUtil redisUtil;

    /**
     * 查询节点配置
     * 
     * @param id 节点配置ID
     * @return 节点配置
     */
    @Override
    public EyeNodeConfig selectEyeNodeConfigById(Long id)
    {
        return eyeNodeConfigMapper.selectEyeNodeConfigById(id);
    }

    /**
     * 查询节点配置列表
     * 
     * @param eyeNodeConfig 节点配置
     * @return 节点配置
     */
    @Override
    public List<EyeNodeConfig> selectEyeNodeConfigList(EyeNodeConfig eyeNodeConfig)
    {
        return eyeNodeConfigMapper.selectEyeNodeConfigList(eyeNodeConfig);
    }

    /**
     * 新增节点配置
     * 
     * @param eyeNodeConfig 节点配置
     * @return 结果
     */
    @Override
    public int insertEyeNodeConfig(EyeNodeConfig eyeNodeConfig)
    {
        eyeNodeConfig.setCreateTime(DateUtils.getNowDate());
        eyeNodeConfig.setCreateUser(ShiroUtils.getLoginName());
        return eyeNodeConfigMapper.insertEyeNodeConfig(eyeNodeConfig);
    }

    /**
     * 修改节点配置
     * 
     * @param eyeNodeConfig 节点配置
     * @return 结果
     */
    @Override
    public int updateEyeNodeConfig(EyeNodeConfig eyeNodeConfig)
    {
        eyeNodeConfig.setUpdateTime(DateUtils.getNowDate());
        eyeNodeConfig.setUpdateUser(ShiroUtils.getLoginName());
        return eyeNodeConfigMapper.updateEyeNodeConfig(eyeNodeConfig);
    }

    /**
     * 删除节点配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEyeNodeConfigByIds(String ids)
    {
        return eyeNodeConfigMapper.deleteEyeNodeConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除节点配置信息
     * 
     * @param id 节点配置ID
     * @return 结果
     */
    @Override
    public int deleteEyeNodeConfigById(Long id)
    {
        return eyeNodeConfigMapper.deleteEyeNodeConfigById(id);
    }
    
    /**
     * 根据产品编码查询对应的节点配置信息
     */
    @Override
    public List<EyeNodeConfig> selectEyeNodeConfigByProductCodeList(EyeNodeConfig eyeNodeConfig)
    {
    	String productCode=eyeNodeConfig.getRelationProductCode();
    	List<EyeNodeConfig> prodNodes=(List<EyeNodeConfig>)redisUtil.hget(RedisConstants.PAGE_NODE_CACHE,productCode);
    	if(prodNodes!=null)return prodNodes;
    	
    	prodNodes=new ArrayList<EyeNodeConfig>();
    	List<EyeNodeConfig> allNode= eyeNodeConfigMapper.selectEyeNodeConfigByProductCodeList(eyeNodeConfig);
    	for(EyeNodeConfig nodeConfigVo:allNode) {
    		String relationProductCode=nodeConfigVo.getRelationProductCode();
    		if(StringUtils.isBlank(relationProductCode)) continue;
    		if(Constants.PROD_ALL.equals(relationProductCode) ) {
    			prodNodes.add(nodeConfigVo);
    			continue;
    		}
    		String[] relatProdCodes=relationProductCode.split(",");
    		for(String relatProdCode:relatProdCodes  ) {
    			if(relatProdCode.indexOf('*')>0) {//带星号的
    				relatProdCode=relatProdCode.substring(0,relatProdCode.length()-1);
    				if(productCode.startsWith(relatProdCode)) {
    					prodNodes.add(nodeConfigVo);
        				break;//匹配到就结束循环
    				}
    			}else if(productCode.equals(relatProdCode)) {
    				prodNodes.add(nodeConfigVo);
    				break;//匹配到就结束循环
    			}
    		}
    	}
		redisUtil.hset(RedisConstants.PAGE_NODE_CACHE,productCode,prodNodes,60*60*1);// 缓存1个小时
		return prodNodes;
    }
}
