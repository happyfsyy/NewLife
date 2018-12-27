package com.example.newlife;

import com.example.utils.LogUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;
    private static final String TAG = "ContentHandler";
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id=new StringBuilder();
        name=new StringBuilder();
        version=new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        LogUtils.e(TAG,"nodeName->"+nodeName);
        nodeName=localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if(nodeName.equals("name")){
            name.append(ch,start,length);
        }else if(nodeName.equals("version")){
            version.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(nodeName)) {
            LogUtils.e(TAG,"id->"+id);
            LogUtils.e(TAG,"name->"+name);
            LogUtils.e(TAG,"version->"+version);
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
