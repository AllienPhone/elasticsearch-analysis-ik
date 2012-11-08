/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 */
package org.elastichsearch.ik.cfx;

import static org.elastichsearch.ik.dic.Dictionary.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.elastichsearch.ik.core.CJKSegmenter;
import org.elastichsearch.ik.core.CN_QuantifierSegmenter;
import org.elastichsearch.ik.core.ISegmenter;
import org.elastichsearch.ik.core.LetterSegmenter;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;

public class Configuration {

	private static String FILE_NAME = "ik/IKAnalyzer.cfg.xml";
	private static final String EXT_DICT = "ext_dict";
	private static final String EXT_STOP = "ext_stopwords";
	private boolean mode=true;
    private static ESLogger logger = null;
	private Properties props;

    @Inject
	public  Configuration(Settings settings){

        logger = Loggers.getLogger("ik-analyzer");
		props = new Properties();
        Environment environment=new Environment(settings);
        File fileConfig= new File(environment.configFile(), FILE_NAME);
        this.mode=settings.getAsBoolean("usermode", true);
        logger.info("Use Mode:"+this.mode);
        InputStream input = null;// Configuration.class.getResourceAsStream(FILE_NAME);
        try {
            input = new FileInputStream(fileConfig);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(input != null){
			try {
				props.loadFromXML(input);
                logger.info("[Dict Loading] {}",FILE_NAME);
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public  List<String> getExtDictionarys(){
		List<String> extDictFiles = new ArrayList<String>(2);
		String extDictCfg = props.getProperty(EXT_DICT);
		if(extDictCfg != null){

			String[] filePaths = extDictCfg.split(";");
			if(filePaths != null){
				for(String filePath : filePaths){
					if(filePath != null && !"".equals(filePath.trim())){
                        File file=new File("ik",filePath.trim());
						extDictFiles.add(file.toString());

					}
				}
			}
		}		
		return extDictFiles;		
	}

	public List<String> getExtStopWordDictionarys(){
		List<String> extStopWordDictFiles = new ArrayList<String>(2);
		String extStopWordDictCfg = props.getProperty(EXT_STOP);
		if(extStopWordDictCfg != null){

			String[] filePaths = extStopWordDictCfg.split(";");
			if(filePaths != null){
				for(String filePath : filePaths){
					if(filePath != null && !"".equals(filePath.trim())){
                        File file=new File("ik",filePath.trim());
						extStopWordDictFiles.add(file.toString());

					}
				}
			}
		}		
		return extStopWordDictFiles;		
	}

	public static List<ISegmenter> loadSegmenter(){
		getInstance();
		List<ISegmenter> segmenters = new ArrayList<ISegmenter>(4);
		segmenters.add(new CN_QuantifierSegmenter());
		segmenters.add(new LetterSegmenter());
		segmenters.add(new CJKSegmenter());
		return segmenters;
	}
	
	/*
	 * 模式切换
	 */
	public  void setUseSmart(boolean useSmart)
	{
		this.mode=useSmart;
	}
	
	/*
	 * Reutrn Mode
	 */
	public boolean useSmart()
	{
		return this.mode;
	}
}
