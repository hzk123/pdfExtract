/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.ScratchFile;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
import javax.xml.stream.events.EndDocument;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import com.giaybac.traprange.PDFTableExtractor;
import com.giaybac.traprange.entity.Table;

public class BoxKeyPosition extends PDFTextStripper
{
    /**
     * Instantiate a new PDFTextStripper object.
     *
     * @throws IOException If there is an error loading the properties.
     */

	static int cnt = 0;
	static List<Text> texts = new ArrayList<Text>();
	
    public BoxKeyPosition() throws IOException
    {
    }

    /**
     * This will print the documents data.
     *
     * @param args The command line arguments.
     *
     * @throws IOException If there is an error parsing the document.
     */
    public static void main( String[] args ) throws IOException
    {
      
        {
            
        	int[] lineIdxs = {0,1,2,3,-1,-2,-3};
        	
            BasicConfigurator.configure();
            PDFTableExtractor extractor = new PDFTableExtractor();
            List<Table> tables =  extractor.setSource("55.pdf")
            	.addPage(28)
            	.exceptLine(lineIdxs)
            	//the last line in each page
            	.extract();
            String html = tables.get(0).toHtml();//table in html format
            String csv = tables.get(0).toString();//table in csv format using semicolon as a delimiter 
            
            FileOutputStream fout = new FileOutputStream("out.html");
            byte[] bytes = html.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                fout.write(bytes[i]);//逐字节写文件
            }
            fout.flush();//强制刷新输出流
            fout.close();//关闭输出流
        }
    }
    
  
    
  
 
}