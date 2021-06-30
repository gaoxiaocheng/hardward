package com.core.utils.license;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取计算机的相关信息
 */
public class LinuxMachineSerial implements MachineSerial {

	@Override
	public String getMainBordId() {

		String result = "";
		String maniBord_cmd = "dmidecode | grep 'Serial Number' | awk '{print $3}' | tail -1";
		Process p;
		try {
			p = Runtime.getRuntime().exec(new String[] { "sh", "-c", maniBord_cmd });// 管道
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
				break;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getMAC() {

		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// linux下的命令，一般取eth0作为本地主网卡
			process = Runtime.getRuntime().exec("ifconfig");
			// 显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null)
			{
				Pattern pat = Pattern.compile("\\b\\w+:\\w+:\\w+:\\w+:\\w+:\\w+\\b");
				Matcher mat= pat.matcher(line);
				if(mat.find())
				{
					mac=mat.group(0);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}
		return mac;
	}

	@Override
	public String getCPUID() {
		String result = "";
		String CPU_ID_CMD = "dmidecode";
		BufferedReader bufferedReader = null;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(new String[] { "sh", "-c", CPU_ID_CMD });// 管道
			bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				// 寻找标示字符串[hwaddr]
				index = line.toLowerCase().indexOf("uuid");
				if (index >= 0) {// 找到了
					// 取出mac地址并去除2边空格
					result = line.substring(index + "uuid".length() + 1).trim();
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.trim();
	}


}
