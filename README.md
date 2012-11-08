ik Analysis for ElasticSearch
==================================



The IK Analysis plugin integrates Lucene IK analyzer into elasticsearch, support customized dictionary.

    --------------------------------------------------
    | String2Integer Analysis Plugin| ElasticSearch  |
    --------------------------------------------------
    | master                        | 0.19 -> master |
    --------------------------------------------------

The plugin includes a `ik` analyzer

�˰��IK�ִʲ����Medcl ik�ִʵĻ����ϣ������ģʽ�л��������˴ʵ䣬ʹ�����µ�IK�㷨������IK�ִʵ�׼ȷ��

����:
�˲μӹ�:  �˲�/�ӹ�
��ѧ��ͥ�� ��ѧ/��ͥ
��������������һЩ�Ľ���

��װ��
   cd bin
   plugin -install AllienPhone/elasticsearch-analysis-ik/1.1.0
   
��װ�ʵ䣺
   cd config
   wget http://github.com/downloads/AllienPhone/elasticsearch-analysis-ik/ik.zip --no-check-certificate
   unzip ik.zip
   rm ik.zip
  
 �ļ����ã�
   index:

   analysis:                   
    analyzer:      
      ik:
          alias: [ik_analyzer]
          type: org.elastichsearch.ik.index.IkAnalyzerProvider
		   UserMode: false  /*�û�����������л�IK�ִʵ�����ģʽ
		   
		   