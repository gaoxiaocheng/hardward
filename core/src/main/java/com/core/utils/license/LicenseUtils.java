package com.core.utils.license;


import com.core.utils.crypto.RSAUtils;
import com.core.utils.crypto.B64Utils;

import java.io.*;
import java.security.Key;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * 用于生成应用所需的license文件
 * 
 * @author aiyoyoyo
 *
 */
public class LicenseUtils {
	private static final String seed = "T*P%NT^X%&IN9-AREXU#3*&KJ$M~QKIF=+^+S^DMTAKVRJRHOI";

	public static void main(String[] args) throws Exception {
		LicenseUtils.s_generate(seed, "e6a4-5c8c-d099ff57-2fbc71d475a85c2f");
	}

	/**
	 * 编码密文内容：模式:机器码(xxxx-xxxx-xxxxxxxx-xxxxxxxxxxxxxxxx):服务日期(yyyy-mm-dd
	 * hh:MM:ss)
	 * 
	 * @param _mode
	 *            模式
	 * @param _code
	 *            机器码
	 * @param _days
	 *            服务日期
	 * @return
	 */
	private static String s_encode_string( int _mode , String _code , String _days ) {
		return _mode + ":" + _code + ":" + _days;
	}
	
	/**
	 * 解码密文内容
	 * @param _data 字符串二进制数组
	 * @return 密文字符串数组
	 */
	public static String[] s_decode_string( byte[] _data ){
		StringTokenizer st = new StringTokenizer( new String( _data ) , ":" );
		
		String[] str = new String[3];
		int idx = 0;
		
		while( st.hasMoreTokens() )
			str[ idx++ ] = st.nextToken();
		
		return str;
	}
	/**
	 * 生成License，并写入文件
	 * @param _seed 种子
	 * @param code 机器码
	 * @throws Exception 错误
	 * @return
	 */
	public static File s_generate(String _seed, String code ) throws Exception{

		Map< String , Key > key_map = RSAUtils.s_genkeys_map( _seed );

		byte[] pri_key = RSAUtils.s_private_key_byte( key_map );
		byte[] pub_key = RSAUtils.s_public_key_byte( key_map );
		String pub_key_str = B64Utils.s_encode( pub_key );
		System.out.println( "用户公钥:" + pub_key_str );

		String str = s_encode_string( LicenseClient.MODE_SINGLE , code , "" );
		byte[] byt_e = RSAUtils.s_encrypt_private( pri_key , str.getBytes() );

		 return s_write_license( pub_key_str , B64Utils.s_encode( byt_e ) );
	}

	/**
	 * 
	 * 将信息写入文件
	 * 
	 * @param _keys 用户公钥
	 * @param _data 加密内容
	 */
	public static File s_write_license( String _keys , String _data ) {
		File f = null;
		try {
			f = File.createTempFile("application","license");

			FileWriter fw = new FileWriter( f );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write( _keys );
			bw.newLine();
			bw.write( _data );

			bw.close();
			fw.close();
		} catch ( Exception e ) {
			System.err.println( "License文件生成失败：" + e.toString()   );
		}
		return f;
	}


	/**
	 * 读取License文件信息
	 * 
	 * @param _file License文件
	 * @return [2]-0:Key 1:Txt
	 */
	public static String[] s_read_license( File _file ) {
		try {
			String[] txt = new String[ 2 ];
			FileReader fr = new FileReader( _file );
			BufferedReader br = new BufferedReader( fr );

			txt[ 0 ] = br.readLine();
			txt[ 1 ] = br.readLine();

			br.close();
			fr.close();

			return txt;
		} catch ( Exception e ) {
			System.err.println( "License文件读取失败：" + e.toString() );
		}
		return null;
	}
}
