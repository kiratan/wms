package wms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.util.JSONPObject;

import com.Master5.main.utils.encrypt.MD5;

public class Test {
	
	public static void main(String[] args) {
		
		System.out.println(MD5.getMD5Pass("admin"));
		
	}

}
