package Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class exportPDF3 {
    
    public static void main(String[] args) throws Exception, DocumentException {
        
        List<String> ponum=new ArrayList<String>();
        add(ponum, 9);
        
        
        //Create Document Instance
        Document document=new Document(PageSize.A3);
        
        //add Chinese font
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        
        //Font headfont=new Font(bfChinese,10,Font.BOLD);
        Font keyfont=new Font(bfChinese,10,Font.NORMAL);
        Font textfont=new Font(bfChinese,8,Font.NORMAL);
        
        //Create Writer associated with document
        PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\meixl\\Downloads\\12345.pdf")));
        
        document.open();
        
        //Seperate Page controller
        int perPage=10;
        int totalPage=ponum.size()%perPage>1? ponum.size()/perPage+1 :ponum.size()/perPage;
        
        for(int j=0;j<totalPage;j++){
            document.newPage();
             
            //create title image
            Image jpeg=Image.getInstance("C:\\Users\\meixl\\Downloads\\w.png");
            jpeg.setAlignment(Image.ALIGN_RIGHT);
            jpeg.scaleAbsolute(150, 37);
            
            //头   公司名   和  logo
            PdfPTable thead = new PdfPTable(2);
            float[] width={2.5f,2f};
            thead.setWidths(width);
            thead.setWidthPercentage(100);
            //thead.setPaddingTop(0);
            //thead.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            Font f = new Font(bfChinese, 18, Font.NORMAL);
            f.setColor(BaseColor.BLACK);
            Paragraph p1 = new Paragraph("上海王的实业",f);
            PdfPCell cell=new PdfPCell(p1);
            cell.setBorder(0);
            //设置居中
            cell.setHorizontalAlignment(2);
            thead.addCell(cell);
            
            cell=new PdfPCell(jpeg);
            cell.setBorder(0);
            cell.setHorizontalAlignment(2);
            thead.addCell(cell);
            
            document.add(thead);
            
            PdfPTable tbody = new PdfPTable(2);
            tbody.setWidths(width);
            tbody.setWidthPercentage(100);
            tbody.setSpacingBefore(10);
            //tbody.setPaddingTop(30);
            //tbody.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            cell=new PdfPCell(new Paragraph("订单号:   "+"R201701051001",keyfont));
            cell.setHorizontalAlignment(3);
            cell.setBorder(0);
            cell.setPadding(3);
            tbody.addCell(cell);
            
            Paragraph p4 = new Paragraph("发件日期:  "+"2017-01-05 13:00:00",keyfont);
            p4.setIndentationRight(41);
            cell=new PdfPCell(new Paragraph(p4));
            cell.setHorizontalAlignment(2);
            cell.setBorder(0);
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("客户采购订单号:  "+"201701051001",keyfont));
            cell.setHorizontalAlignment(3);
            cell.setBorder(0); 
            cell.setPadding(3);
            tbody.addCell(cell);
            
            Font f2 = new Font(bfChinese, 12, Font.NORMAL);
            f2.setColor(BaseColor.RED);
            Paragraph p6 = new Paragraph("客户名称:  "+"梅小龙",f2);
            p6.setIndentationRight(75);
            cell=new PdfPCell(p6);
            cell.setHorizontalAlignment(2);
            cell.setBorder(0);
            tbody.addCell(cell);
            
            document.add(tbody);
            
            tbody = new PdfPTable(22);
            float[] width2={1f,2f,1f,1f,1f,2f,1f,1f,1f,1f,1f,1f,1f,1f,1f,2f,1f,1f,1f,1f,1f,1f};
            tbody.setWidths(width2);
            tbody.setWidthPercentage(100);
            tbody.setSpacingBefore(8);
            
            tbody.getDefaultCell().setBorder(1);
            tbody.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            tbody.getDefaultCell().setBorderWidthRight(1);
            tbody.getDefaultCell().setBorderColorRight(BaseColor.LIGHT_GRAY);
            //tbody.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            cell=new PdfPCell(new Paragraph("Supplier(卖方):",textfont));
            cell.setHorizontalAlignment(2);
            cell.setColspan(2);
            cell = setBorder(cell,"topLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("抚顺天泽包装制品有限公司",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"topLeft");
            tbody.addCell(cell);
            
            
            cell=new PdfPCell(new Paragraph("Address(联系地址):",textfont));
            cell.setHorizontalAlignment(2);
            cell.setColspan(3);
            cell = setBorder(cell,"topLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("抚顺市望花区鞍山路西段56号",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"topLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("订购日期:",textfont));
            cell.setHorizontalAlignment(2);
            cell.setColspan(2);
            cell = setBorder(cell,"topLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("2016/12/25",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"topRight");
            tbody.addCell(cell);
            
            //第二行
            cell=new PdfPCell(new Paragraph("Contact(联系人):",textfont));
            cell.setHorizontalAlignment(2);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("梅小龙",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);

            cell=new PdfPCell(new Paragraph("Tel(电话):",textfont));
            cell.setHorizontalAlignment(2);
            cell.setColspan(3);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("15012121221",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("交货方式:",textfont));
            cell.setHorizontalAlignment(2);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("空运",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            document.add(tbody);
            
            //第三行
            tbody = new PdfPTable(22);
            tbody.setWidths(width2);
            tbody.setWidthPercentage(100);
            tbody.setPaddingTop(0);
            
            tbody.getDefaultCell().setBorder(1);
            tbody.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
            tbody.getDefaultCell().setBorderWidthRight(1);
            tbody.getDefaultCell().setBorderColorRight(BaseColor.LIGHT_GRAY);
            //tbody.getDefaultCell().setPadding(5);
            
            cell=new PdfPCell(new Paragraph("序号",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("Item#品名",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("规格",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("瓦楞",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("用纸颜色",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("配材",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("尺寸",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("印刷颜色",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("印刷版面",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("每包只数",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("下单包数",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("下单数量",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("价格(元/只)",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("总价",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("UPC码",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("刀模",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("是否放宣传单",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("备注",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("每包毛重(KG)",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("每包体积(M3)",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setRowspan(2);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("长",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("宽",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("高",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            for(int i=0;i<3;i++){
            	cell=new PdfPCell(new Paragraph((i+1)+"",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerLeft");
                tbody.addCell(cell);
                
                cell=new PdfPCell(new Paragraph("",textfont));
                cell.setHorizontalAlignment(1);
                cell = setBorder(cell,"centerRight");
                tbody.addCell(cell);
            }
            //合计行
            cell=new PdfPCell(new Paragraph("合计",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("100",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("10000",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("说明",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(20);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("销售联系人",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("电话",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("制单人",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("运营中心审核",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("备注",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            cell = setBorder(cell,"centerLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(20);
            cell = setBorder(cell,"centerRight");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("供应商确认",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            cell = setBorder(cell,"bottomLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"bottomLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("确认日期",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell = setBorder(cell,"bottomLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(5);
            cell = setBorder(cell,"bottomLeft");
            tbody.addCell(cell);
            
            cell=new PdfPCell(new Paragraph("请于1个工作日内回传此订单，否则视为默认.",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(7);
            cell = setBorder(cell,"bottomRight");
            tbody.addCell(cell);
            
            Paragraph foot11 = new Paragraph("文件只作  Foster 收貨用"+printBlank(215)+"____________________",keyfont);
            cell=new PdfPCell(foot11);
            cell.setHorizontalAlignment(3);
            cell.setColspan(22);
            cell.setBorder(0);
            tbody.addCell(cell);
            
            Paragraph foot12 = new Paragraph("Printed from Foster supplier portal"+printBlank(198)+"上海王的实业有限公司"+printBlank(25)+"版本: 1.0",keyfont);
            cell=new PdfPCell(foot12);
            cell.setHorizontalAlignment(3);
            cell.setColspan(22);
            cell.setBorder(0);
            tbody.addCell(cell);
            
            document.add(tbody);
            
            if(j+1==totalPage){
            	/* Paragraph foot11 = new Paragraph("文件只作  Foster 收貨用"+printBlank(185)+"____________________",keyfont);
                document.add(foot11);
                Paragraph foot12 = new Paragraph("Printed from Foster supplier portal"+printBlank(168)+"上海王的实业有限公司"+printBlank(70)+"版本: 1.0",keyfont);
                document.add(foot12);
                HeaderFooter footer11=new HeaderFooter(foot11, true);
                footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
                HeaderFooter footer12=new HeaderFooter(foot12, true);
                footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);*/
            }
        }
        document.close();
    }
    
    private static PdfPCell setBorder(PdfPCell cell,String border){
    	if(border.equals("topLeft")){
	    	cell.setBorderColorTop(BaseColor.BLACK);
	    	cell.setBorderWidthTop(1);
	    	
	    	cell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthLeft(1);
	    	
	    	cell.setBorderWidthRight(0);
	    	cell.setBorderWidthBottom(0);
    	}else if(border.equals("topRight")){
    		cell.setBorderColorTop(BaseColor.BLACK);
	    	cell.setBorderWidthTop(1);
	    	
	    	cell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthLeft(1);
	    	
	    	cell.setBorderColorRight(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthRight(1);
	    	
	    	cell.setBorderWidthBottom(0);
    	}else if(border.equals("centerLeft")){
    		cell.setBorderColorTop(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthTop(1);
	    	
	    	cell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthLeft(1);
	    	
	    	cell.setBorderWidthRight(0);
	    	cell.setBorderWidthBottom(0);
    	}else if(border.equals("centerRight")){
    		cell.setBorderColorTop(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthTop(1);
	    	
	    	cell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthLeft(1);
	    	
	    	cell.setBorderColorRight(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthRight(1);
	    	
	    	cell.setBorderWidthBottom(0);
    	}else if(border.equals("bottomLeft")){
    		cell.setBorderColorTop(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthTop(1);
	    	
	    	cell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthLeft(1);
	    	
	    	cell.setBorderWidthRight(0);
	    	
	    	cell.setBorderColorRight(BaseColor.BLACK);
	    	cell.setBorderWidthBottom(1);
    	}else if(border.equals("bottomRight")){
    		cell.setBorderColorTop(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthTop(1);
	    	
	    	cell.setBorderColorLeft(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthLeft(1);
	    	
	    	cell.setBorderColorRight(BaseColor.LIGHT_GRAY);
	    	cell.setBorderWidthRight(1);
	    	
	    	cell.setBorderColorBottom(BaseColor.BLACK);
	    	cell.setBorderWidthBottom(1);
    	}else{
	    	cell.setBorder(0);
    	}
    	cell.setPadding(8);
		return cell;
    }
    
    
    public static String leftPad(String str, int i) {
        int addSpaceNo = i-str.length();
        String space = ""; 
        for (int k=0; k<addSpaceNo; k++){
                space= " "+space;
        };
        String result =space + str ;
        return result;
     }
    
    public static void add(List<String> list,int num){
        for(int i=0;i<num;i++){
            list.add("test"+i);
        }
    }
    
    public static String printBlank(int tmp){
          String space="";
          for(int m=0;m<tmp;m++){
              space=space+" ";
          }
          return space;
    }

}