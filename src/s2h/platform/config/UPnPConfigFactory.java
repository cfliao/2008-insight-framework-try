package s2h.platform.config;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import s2h.platform.annotation.UPnPParameter;
import s2h.platform.support.IOUtilz;

/**
 * �̿�J�� UPnP modelName �P friendlyName �ʺA���� UPnP Device Description
 *
 * modelName �������W�h�� xx.yy.zz
 * friendlyName �i�t�ťջP��L xml �X�k�r��
 *
 * ���F friendlyName ���~�AmodelName �|�Q�ഫ�ϥΡG
 * �ഫ�� URL ���@�����G xx.yy.zz ==> xx/yy/zz
 * �ഫ�� ServiceType ���@����: xx.yy.zz ==> xx (�u���Ĥ@�� Component)
 * �ഫ�� ServiceId ���@����: xx.yy.zz ==> xx-yy-zz
 *
 * @author qrtt1
 */
public class UPnPConfigFactory {

	static Log log = LogFactory.getLog(UPnPConfigFactory.class);

	public static UPnPConfig createConfig(UPnPParameter param, Package nodePackage){
		String mname = StringUtils.isBlank(param.modelName()) ?  nodePackage.getName() : param.modelName();
		String fname = StringUtils.isBlank(param.friendlyName()) ?  mname : param.friendlyName();
		return UPnPConfigFactory.createConfig(mname, fname);
	}

	public static UPnPConfig createConfig(UPnPParameter param) {
		return createConfig(param.modelName(), param.friendlyName());
	}

	public static UPnPConfig createTestConfig() {
		UPnPConfig config = new UPnPConfig();
		try {
			config.setDeviceDescription(FileUtils
					.readFileToString(new File(UPnPConfig.class.getResource(
							"device-sample.xml").getFile())));
			config.setServiceDescription(FileUtils.readFileToString(new File(
					UPnPConfig.class.getResource("service-template.xml")
							.getFile())));
		} catch (IOException e) {
			log.error("", e);
		}

		config.setServiceName("urn:schemas-upnp-org:service:vcom:1");

		return config;

	}

	/**
	 * @param modelName
	 * @param friendlyName
	 * @return
	 */
	public static UPnPConfig createConfig(String modelName, String friendlyName) {
		log.info("Model Name: " + modelName);
		log.info("Friendly Name: " + friendlyName);

		UPnPConfig config = new UPnPConfig();

		try {
			config.setDeviceDescription(
					/*
					 * new File(...) will occur file not found exception when the file in jar archive,
					 * use getResourceAsStream and read to string is ok
					 * */
					//FileUtils.readFileToString(new File(UPnPConfig.class.getResource("device-template.xml").getFile()))
					IOUtilz.readLinesToString(UPnPConfig.class.getResourceAsStream("device-template.xml"))
						.replaceAll("\\$\\{MODEL_NAME\\}", modelName)
						.replaceAll("\\$\\{MODEL_NAME:URL\\}", NamingConverionUtils.dot2Slash(modelName))
						//.replaceAll("\\$\\{MODEL_NAME:TYPE\\}", NamingConverionUtils.firstPart(modelName))
						.replaceAll("\\$\\{MODEL_NAME:TYPE\\}", NamingConverionUtils.dot2Camel(modelName))
						.replaceAll("\\$\\{MODEL_NAME:ID\\}", NamingConverionUtils.dot2Dash(modelName))
						.replaceAll("\\$\\{FRIENDLY_NAME\\}", friendlyName)
					);

			// config.setServiceDescription(FileUtils.readFileToString(new File(
			// UPnPConfig.class.getResource("service-template.xml")
			// .getFile())));

			config.setServiceDescription(IOUtilz.readLinesToString(UPnPConfig.class.getResourceAsStream("service-template.xml")));

			config.setServiceName("urn:schemas-upnp-org:service:${MODEL_NAME:TYPE}:1"
				.replaceAll("\\$\\{MODEL_NAME:TYPE\\}", NamingConverionUtils.dot2Camel(modelName)));

		} catch (Exception e) {
			log.error("", e);
			return null;
		}

		return config;
	}

}
