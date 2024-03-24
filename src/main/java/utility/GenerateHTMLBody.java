package utility;

import java.util.Map;

public class GenerateHTMLBody{

    String htmlText;
    public String generateHtmlBody(Map<String,String> mapData) throws Exception
    {
        htmlText=Constants.HTML_TABLEHEADER;
        for (Map.Entry<String,String> map : mapData.entrySet())
        {

            htmlText = htmlText + "<tr align='center' border ='2'>"+"<td>" + map.getKey() + "</td>"
                    + "<td>" + map.getValue() + "</td>"+"</tr>";
        }

        return htmlText;

    }
}
