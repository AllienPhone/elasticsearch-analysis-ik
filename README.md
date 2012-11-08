ik Analysis for ElasticSearch
==================================



The IK Analysis plugin integrates Lucene IK analyzer into elasticsearch, support customized dictionary.

    --------------------------------------------------
    | String2Integer Analysis Plugin| ElasticSearch  |
    --------------------------------------------------
    | master                        | 0.19 -> master |
    --------------------------------------------------

The plugin includes a `ik` analyzer

此版的IK分词插件在Medcl ik分词的基本上，添加了模式切换，更新了词典，使用最新的IK算法，增加IK分词的准确性

如在:
人参加工:  人参/加工
科学家庭： 科学/家庭
等岐义词上面做了一些改进。

安装：
   cd bin
   plugin -install AllienPhone/elasticsearch-analysis-ik/1.1.0
   
安装词典：
   cd config
   wget http://github.com/downloads/AllienPhone/elasticsearch-analysis-ik/ik.zip --no-check-certificate
   unzip ik.zip
   rm ik.zip
  
 文件配置：
   index:

   analysis:                   
    analyzer:      
      ik:
          alias: [ik_analyzer]
          type: org.elastichsearch.ik.index.IkAnalyzerProvider
		   UserMode: false  /*用户在这里可以切换IK分词的两种模式
		   
		   