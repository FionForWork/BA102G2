package aom.android.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import com.tradition.model.*;
import com.tradition_type.model.Tradition_TypeVO;
import com.tradition_type.model.Tradition_Type_Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.placeview.model.PlaceViewVO;
import com.quote.model.QuoteService;
import com.quote.model.QuoteVO;
import com.reservation.model.ReservationService;
import com.reservation.model.ReservationVO;
import com.rfq.model.RFQService;
import com.rfq_detail.model.RFQ_DetailService;
import com.rfq_detail.model.RFQ_DetailVO;
import com.serv.model.*;
import com.works.model.*;
import com.album.model.AlbumService;
import com.album.model.AlbumVO;
import com.calendar.model.CalendarService;
import com.calendar.model.CalendarVO;
import com.com.model.*;
import com.comtra.model.ComTraService;
import com.comtra.model.ComTraVO;
import com.content.model.ContentService;
import com.content.model.ContentVO;
import com.service_type.model.Service_TypeService;
import com.service_type.model.Service_TypeVO;
import com.temp.model.TempService;
import com.temp.model.TempVO;
import com.tempcont.model.TempContService;
import com.tempcont.model.TempContVO;
import java.lang.reflect.Type;


@WebServlet("/AndroidService")
public class AndroidService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	

	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		rq.setCharacterEncoding("UTF-8");
		ServService service=new ServService();
	
		Gson gson=new Gson();
		String outStr = "";
		List<ServVO> dao=service.getAll();
		BufferedReader br = rq.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);	
		}
		if(jsonIn.length()>0)
		{
			JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
			String action = jsonObject.get("action").getAsString();
			
			if(action.equals("popular"))
			{   		
				outStr=gson.toJson(dao);		
			}		
			else if(action.equals("getLogo"))
			{	
				String com_no=jsonObject.get("com_no").getAsString();
				String styp=jsonObject.get("STYPE_NO").getAsString();
				ComVO com=new ComService().getOneCom(com_no);
				Service_TypeVO sevo=new Service_TypeService().getOne(styp);
				List<WorksVO> picture=new WorksService().getAllByComNo(com_no);
				JsonObject js=new JsonObject();
				byte[] bict=null;
				if(picture.size()>0)
				{
					bict=picture.get(0).getImg();
					js.addProperty("picture", Base64.getEncoder().encodeToString(bict));
				}else
				{
					js.addProperty("picture"," ");
				}
			
				com_no=gson.toJson(com);
				styp=gson.toJson(sevo);
				js.addProperty("comm", com_no);
				js.addProperty("styp", styp);
				outStr=js.toString();
			}else if(action.equals("getComn"))
			{
				String com_no=jsonObject.get("com_no").getAsString();
				ComVO com=new ComService().getOneCom(com_no);
				JsonObject js=new JsonObject();
				dao=service.getCom(com_no);
				com_no=gson.toJson(dao);
				js.addProperty("service",com_no);
				js.addProperty("logo", Base64.getEncoder().encodeToString(com.getLogo()));
				outStr=js.toString();			
			}else if(action.equals("getTra"))
			{
				List<TraditionVO> tt=new Tradition_Service().getAll();
				outStr=gson.toJson(tt);
				
				
			}else if(action.equals("getTratype"))
			{
				String tratype=jsonObject.get("tratype").getAsString();
				Tradition_TypeVO tt=new Tradition_Type_Service().getOneTradition_Type(Integer.valueOf(tratype));
				outStr=tt.getType();
			}else if(action.equals("enter"))
			{
				JsonObject jo=new JsonObject();
				String menname=jsonObject.get("username").getAsString();
				String idertify=jsonObject.get("idertify").getAsString();
				try{
					if(idertify.equals("men"))
					{
					  
					  MemVO men=new MemService().getOneMemById(menname);
					  jo.addProperty("no", men.getMem_no());
					  jo.addProperty("password", men.getPwd());
					  jo.addProperty("staue", men.getStatus());
					  jo.addProperty("name", men.getName());
					  System.out.printf("men enter"+men.getMem_no());
					  
					}else if(idertify.equals("busi"))
					{
					   ComVO com=new ComService().getOneComById(menname);
					   jo.addProperty("no",com.getCom_no());
					   jo.addProperty("password",com.getPwd());
					   jo.addProperty("staue",com.getStatus());  
					   jo.addProperty("name", com.getName());
					}
				}catch(NullPointerException e){
					jo.addProperty("password"," ");
					jo.addProperty("staue"," ");
				}
				outStr=jo.toString();
				
		    }else if(action.equals("getWorks"))
			{
				String com_no=jsonObject.get("com_no").getAsString();
				List<WorksVO> picture=new WorksService().getAllByComNo(com_no);
				JsonObject js=new JsonObject();
				for(int i=0;i<picture.size();i++)
				{
				   byte[] bict=picture.get(i).getImg();
				   js.addProperty(String.valueOf(i),Base64.getEncoder().encodeToString(bict));
				}
				outStr=js.toString();
			}else if(action.equals("search"))
			{
				String search=jsonObject.get("search").getAsString();
				String h="%"+search+"%";
				List<ServVO> sh=new ServService().getSearch(h);
				search=gson.toJson(sh);
				outStr=search;
			}else if(action.equals("getLo"))
			{
				String northeast=jsonObject.get("northeast").getAsString();
				String southwest=jsonObject.get("southwest").getAsString();
				String latMax=northeast.substring(northeast.indexOf("(")+1,northeast.indexOf(","));
				String latMin=southwest.substring(southwest.indexOf("(")+1,southwest.indexOf(","));
				String lngMax=northeast.substring(northeast.indexOf(",")+1,northeast.indexOf(")"));
				String lngMin=southwest.substring(southwest.indexOf(",")+1,southwest.indexOf(")"));
				List<ComVO> com=new ComService().getLocation(lngMin.trim(), lngMax.trim(), latMin.trim(), latMax.trim());
				List<ServVO> serlist=new ArrayList<>();
				for(int i=0;i<com.size();i++)
				{
					List<ServVO> ser=new ServService().getCom(com.get(i).getCom_no());
					if(ser.size()>0)
					{
						serlist.add(ser.get(0));
					}					
				}
				JsonObject js=new JsonObject();
				js.addProperty("comm",gson.toJson(com).toString());
				js.addProperty("servo",gson.toJson(serlist).toString());
				outStr=js.toString();
			}else if(action.equals("getSpot"))
			{
				String northeast=jsonObject.get("northeast").getAsString();
				String southwest=jsonObject.get("southwest").getAsString();
				String latMax=northeast.substring(northeast.indexOf("(")+1,northeast.indexOf(","));
				String latMin=southwest.substring(southwest.indexOf("(")+1,southwest.indexOf(","));
				String lngMax=northeast.substring(northeast.indexOf(",")+1,northeast.indexOf(")"));
				String lngMin=southwest.substring(southwest.indexOf(",")+1,southwest.indexOf(")"));
				
				List<PlaceVO> place=new PlaceService().getSome(latMin.trim(), lngMin.trim(), latMax.trim(), lngMax.trim());		
				outStr=gson.toJson(place);
			}else if(action.equals("getspotpicture"))
			{
				String plano=jsonObject.get("plano").getAsString();
				List<String> pvno=new PlaceViewService().getAllByFk(plano);
				if(pvno!=null&&pvno.size()>1)
				{
					PlaceViewVO placevo=new PlaceViewService().getOneByPK(pvno.get(0));
					byte[] bict= placevo.getImg();
					outStr=Base64.getEncoder().encodeToString(bict);
				}else{
					outStr="";
				}
			}else if(action.equals("getSpotviewpicture"))
			{
				String plano=jsonObject.get("plano").getAsString();
				PlaceViewVO placevo=new PlaceViewService().getOneByPK(plano);
				byte[] bict= placevo.getImg();
				outStr=Base64.getEncoder().encodeToString(bict);
				
			}else if(action.equals("getallSpotno"))
			{
				String plano=jsonObject.get("plano").getAsString();
				List<String> pvno=new PlaceViewService().getAllByFk(plano);
				outStr=gson.toJson(pvno);
			}else if(action.equals("checkusername"))
			{
				String checkusername=jsonObject.get("checkusername").getAsString();
				String idertify=jsonObject.get("idertify").getAsString();
				try{
					if(idertify.equals("men"))
					{
					  MemVO men=new MemService().getOneMemById(checkusername);
					  outStr=men.getId();
					}else if(idertify.equals("busi"))
					{
					   ComVO com=new ComService().getOneComById(checkusername);
					   outStr=com.getId();
					}
				}catch(NullPointerException e){
					outStr="";
				}
			}else if(action.equals("updatemen"))
			{
				String name=jsonObject.get("name").getAsString();
				String userid=jsonObject.get("userid").getAsString();
				String password=jsonObject.get("password").getAsString();
				String phone=jsonObject.get("phone").getAsString();
				String birthday=jsonObject.get("birthday").getAsString();
				String sex=jsonObject.get("sex").getAsString();
				String acount=jsonObject.get("acount").getAsString();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date parseDate=new Date(System.currentTimeMillis());
				try {
					parseDate = (Date) sdf.parse(birthday);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] picture=new byte[22];
				MemVO men=new MemService().addMem(userid, password, name, sex, parseDate, phone, userid, acount, picture);
				
		}else if(action.equals("updatebus"))
		{
			String name=jsonObject.get("name").getAsString();
			String userid=jsonObject.get("userid").getAsString();
			String password=jsonObject.get("password").getAsString();
			String phone=jsonObject.get("phone").getAsString();
			String desc=jsonObject.get("desc").getAsString();
			String address=jsonObject.get("address").getAsString();
			String lat=jsonObject.get("lat").getAsString();
			String lng=jsonObject.get("lng").getAsString();
			String acount=jsonObject.get("acount").getAsString();
			byte[] picture=new byte[22];
			
			ComVO com=new ComService().addCom(userid, password, name, address, desc, phone, acount, picture, lng, lat);
			
	     }else if(action.equals("changepassword"))
			{
				String checkusername=jsonObject.get("checkusername").getAsString();
				String idertify=jsonObject.get("idertify").getAsString();
				String pwd=jsonObject.get("password").getAsString();
				boolean forget=jsonObject.get("forget").getAsBoolean();
				try{
					if(idertify.equals("men"))
					{
					  MemVO men=new MemService().getOneMemById(checkusername);
					  MemVO m=new MemService().updatePwd(men.getMem_no(), pwd);
					  outStr=m.getPwd();
					}else if(idertify.equals("busi")&&forget)
					{
					   ComVO com=new ComService().getOneComById(checkusername);
					   ComVO c=new ComService().updatePwd(com.getCom_no(), pwd);
					   outStr=com.getId();
					}else if(idertify.equals("busi")&&!forget)
					{
					   ComVO com=new ComService().getOneComById(checkusername);
					   ComVO c=new ComService().confirmCom(com.getCom_no());
					   outStr=c.getStatus();
					}
				}catch(NullPointerException e){
					outStr="xx";
				}
	
			}else if(action.equals("getReservation"))
			{
				String no=jsonObject.get("men").getAsString();
				String date=jsonObject.get("date").getAsString();
				String year=date.substring(0,date.indexOf("-"));
				String month=date.substring(date.indexOf("-")+1,date.lastIndexOf("-"));
				String today=date.substring(date.lastIndexOf("-")+1);
				List<ReservationVO> res=new ReservationService().getMonthCalendar(Integer.valueOf(year), (Integer.valueOf(month)),(Integer.valueOf(today)),no);
				JsonObject jout=new JsonObject();
				ArrayList<String> comname=new ArrayList<>();
				ArrayList<String> sername=new ArrayList<>();
				if(res!=null)
				{
					 for(int i=0;i<res.size();i++)
                    {
						 ServVO ser=new ServService().getOneServ(res.get(i).getServ_no());
						 ComVO  com=new ComService().getOneCom(res.get(i).getCom_no());
						 comname.add(com.getName());
						 sername.add(ser.getTitle()); 
                    }
				}
				jout.addProperty("com", gson.toJson(comname));
				jout.addProperty("sername",gson.toJson(sername));
                outStr=jout.toString();
			}else if(action.equals("getComdate"))
			{
				String serviceno=jsonObject.get("service").getAsString();
				ServVO ser=new ServService().getOneServ(serviceno);
				ComVO com=new ComService().getOneCom(ser.getCom_no());
				JsonObject jo=new JsonObject();
				jo.addProperty("service",ser.getTitle() );
				jo.addProperty("com",com.getName());
				outStr=jo.toString();
			}else if(action.equals("getcalender"))
			{
				String com=jsonObject.get("com").getAsString();
				String year=jsonObject.get("year").getAsString();
				String month=jsonObject.get("month").getAsString();
				String dayNum=jsonObject.get("dayNum").getAsString();
				List<CalendarVO> cla=new CalendarService().getMonthCalendar(Integer.valueOf(year), (Integer.valueOf(month)+1),(Integer.valueOf(dayNum)), com);
				List<ServVO> ss=new ServService().getCom(com);
				String servicevo=gson.toJson(ss);
				String comvo=gson.toJson(cla);
				JsonObject js=new JsonObject();
				js.addProperty("com", comvo);
				js.addProperty("ser", servicevo);
				outStr=js.toString();
			}else if(action.equals("addnewreser"))
			{
				String ser=jsonObject.get("ser").getAsString();
				String username=jsonObject.get("username").getAsString();
				String cc=jsonObject.get("Calender").getAsString();
				ServVO servo=gson.fromJson(ser, ServVO.class);
				MemVO men=new MemService().getOneMemById(username);
				CalendarVO calen=gson.fromJson(cc,CalendarVO.class);
				calen.setCom_no(servo.getCom_no());
		        Calendar cal = Calendar.getInstance();
		        new ReservationService().addReservation(men.getMem_no(), servo.getCom_no(), new Timestamp(cal.getTimeInMillis()), calen.getCal_date(), servo.getServ_no(), servo.getStype_no(), servo.getPrice(),"0", calen);
			}else if(action.equals("getmessagelog"))
			{
				String no=jsonObject.get("no").getAsString();
				ComVO com=new ComService().getOneCom(no);
				outStr=gson.toJson(com);
			}else if(action.equals("getcloudyalum"))
			{
				String no=jsonObject.get("no").getAsString();
				List<AlbumVO> alb=new AlbumService().getAllByMemNo(no);
				if(alb!=null)
				{
				 for(int i=0;i<alb.size();i++)
				  {				
					 alb.get(i).setCover(null);
				  }
				}
				outStr=gson.toJson(alb);
			}else if(action.equals("getcloudypicture"))
			{
				String no=jsonObject.get("no").getAsString();
				AlbumVO alb=new AlbumService().getOneAlbum(no);
				if(alb!=null)
				{
				  outStr=Base64.getEncoder().encodeToString(alb.getCover());
				}else
				{
				  outStr="";
				}
			}else if(action.equals("getcontent"))
			{
				String no=jsonObject.get("no").getAsString();
				List<ContentVO> albcontent=new ContentService().getAllByAlbNo(no);
				if(albcontent!=null)
				{
				 for(int i=0;i<albcontent.size();i++)
				  {				
					 albcontent.get(i).setImg(null);;
				  }
				}
				outStr=gson.toJson(albcontent);
			}else if(action.equals("getcontentpicture"))
			{
				String no=jsonObject.get("no").getAsString();
				ContentVO onecontent=new ContentService().getOneContent(no);
				if(onecontent!=null)
				{
				  outStr=Base64.getEncoder().encodeToString(onecontent.getImg());
				}else
				{
				  outStr="";
				}
			}else if(action.equals("getmyonecloudy"))
			{
				String no=jsonObject.get("no").getAsString();
				List<AlbumVO> alb=new AlbumService().getAllByMemNo(no);
				outStr=Base64.getEncoder().encodeToString(alb.get(0).getCover());
			}else if(action.equals("getmynochoice"))
			{
				String no=jsonObject.get("no").getAsString();
				List<TempVO> tt=new TempService().getAllByMemNo(no);
				List<TempContVO> temp=new TempContService().getAllByTempNo(tt.get(0).getTemp_no());
				outStr=Base64.getEncoder().encodeToString(temp.get(0).getImg());
			}else if(action.equals("getTemp"))
			{
				String no=jsonObject.get("no").getAsString();
				List<TempVO> tt=new TempService().getAllByMemNo(no);
				outStr=gson.toJson(tt);
			}else if(action.equals("getonetempVO"))
			{
				String no=jsonObject.get("no").getAsString();
				List<TempContVO> tt=new TempContService().getAllByTempNo(no);
				outStr=Base64.getEncoder().encodeToString(tt.get(0).getImg());
				
			}else if(action.equals("getalltemp"))
			{
				String no=jsonObject.get("no").getAsString();
				System.out.println("XXXXXXXXXXXXXXX"+no);
				List<TempContVO> tt=new TempContService().getAllByTempNo(no);
				for(int i=0;i<tt.size();i++)
				{
					tt.get(i).setImg(null);
				}
				outStr=gson.toJson(tt);
			}else if(action.equals("getonetemppicture"))
			{
				String no=jsonObject.get("no").getAsString();
				TempContVO tt=new TempContService().getOneTempCont(no);
				outStr=Base64.getEncoder().encodeToString(tt.getImg());
			}else if(action.equals("addpicturetoalbum"))
			{
				String choice=jsonObject.get("choice").getAsString();
				String nochoice=jsonObject.get("nochoice").getAsString();
				Type listType = new TypeToken<List<String>>() {}.getType();
	            List<String> choicepicture = gson.fromJson(choice, listType);
	            List<String> nochoicepicture = gson.fromJson(nochoice, listType);
	            TempContVO temcont=new TempContService().getOneTempCont(choicepicture.get(0));
	            TempVO t=new TempService().getOneTemp(temcont.getTemp_no());
	            t=new TempService().updateTemp(t.getTemp_no(), t.getCom_no(), t.getMem_no(), t.getName(), t.getCreate_date(), t.getAvailable(), "已挑選");
	            AlbumVO alb=new AlbumService().addAlbum(t.getMem_no(), t.getName(), temcont.getImg(), new Timestamp(System.currentTimeMillis()));
	            for(int i=0;i<choicepicture.size();i++)
	            {
	               TempContVO temdetail=new TempContService().getOneTempCont(choicepicture.get(i));
	               new ContentService().addContent(alb.getAlb_no(), temdetail.getUpload_date(), temdetail.getImg(), null);
	            }
	            for(int i=0;i<nochoicepicture.size();i++)
	            {
	            	new TempContService().deleteTempCont(nochoicepicture.get(i));
	            }
			}else if(action.equals("getReservationoder"))
			{
				String no=jsonObject.get("no").getAsString();
				List<ReservationVO> res=new ReservationService().getAllMemRes(no);
				outStr=gson.toJson(res);
			}else if(action.equals("getorderserver"))
			{
				String com=jsonObject.get("com").getAsString();
				String ser=jsonObject.get("ser").getAsString();
				ComVO c=new ComService().getOneCom(com);
				ServVO s=new ServService().getOneServ(ser);
				JsonObject jout=new JsonObject();
				jout.addProperty("com", gson.toJson(c));
				jout.addProperty("ser", gson.toJson(s));
				outStr=jout.toString();
			}else if(action.equals("updateStatue"))
			{
				String res=jsonObject.get("res").getAsString();
				ReservationVO resvation=gson.fromJson(res, ReservationVO.class);
				String statue="";
				if(resvation.getServ_no().indexOf("7")==0)
				{
					statue="4";
				}else {
				  statue=jsonObject.get("statue").getAsString();
				}
				new ReservationService().updateStatus(statue,resvation.getRes_no());
			}else if(action.equals("updateScore"))
			{
				String res=jsonObject.get("res").getAsString();
				ReservationVO resvation=gson.fromJson(res, ReservationVO.class);
				String statue=jsonObject.get("statue").getAsString();
				String score=jsonObject.get("score").getAsString();
				new ReservationService().updateScore(statue, Integer.valueOf(score.substring(0,1)), resvation.getRes_no(), resvation.getServ_no());
			}else if(action.equals("addnewrfq"))
			{
				String addres=jsonObject.get("addres").getAsString();
				String serve=jsonObject.get("serve").getAsString();
				String date=jsonObject.get("date").getAsString();
				String needrequest=jsonObject.get("needrequest").getAsString();
				String no=jsonObject.get("no").getAsString();
				String d=date+" 00:00:00";
				List<RFQ_DetailVO> list=new ArrayList<>();
				RFQ_DetailVO rfq=new RFQ_DetailVO();
				rfq.setContent(needrequest);
				rfq.setStype_no("000"+serve);
				rfq.setSer_date(Timestamp.valueOf(d));
				rfq.setLocation(addres);
				rfq.setStatus("1");
				list.add(rfq);		
			    new RFQService().addRFQ(no, new Timestamp(System.currentTimeMillis()), list);           
			}else if(action.equals("getrfqall"))
			{
				String no=jsonObject.get("no").getAsString();
				List<RFQ_DetailVO> rfq=new RFQ_DetailService().getMyRFQDetail(no);
				ArrayList<Integer> size=new ArrayList();
				for(RFQ_DetailVO r:rfq)
				{
					List<QuoteVO> q=new QuoteService().getAllQuote(r.getRfqdetail_no());
					size.add(q.size());
				}
				JsonObject jout=new JsonObject();
				jout.addProperty("rfq", gson.toJson(rfq));
				jout.addProperty("size", gson.toJson(size));
				outStr=jout.toString(); 
				
			}else if(action.equals("changerfqstyle"))
			{
				String rfq=jsonObject.get("rfq").getAsString();
				RFQ_DetailVO t=gson.fromJson(rfq, RFQ_DetailVO.class);
				System.out.println(t.getStatus()+"  "+t.getRfq_no());
				new RFQ_DetailService().updateRFQDetailStatus(t.getStatus(), t.getRfqdetail_no());
			}else if(action.equals("getallquto"))
			{
				String rfq=jsonObject.get("no").getAsString();
				List<QuoteVO> q=new QuoteService().getAllQuote(rfq);
				ArrayList<ComVO> c=new ArrayList<>();
				for(int i=0;i<q.size();i++)
				{
				    ComVO cc=new ComService().getOneCom(q.get(i).getCom_no());
				    c.add(cc);
				}
				JsonObject jout=new JsonObject();
				jout.addProperty("quto", gson.toJson(q));
				jout.addProperty("com", gson.toJson(c));
				outStr=jout.toString();
			}else if(action.equals("updatechoicequto"))
			{
				String project=jsonObject.get("project").getAsString();
				QuoteVO q=gson.fromJson(project, QuoteVO.class);
				String c=jsonObject.get("com").getAsString();
				String menno=jsonObject.get("men").getAsString();
				String name=jsonObject.get("name").getAsString();
				String rfqs=jsonObject.get("rfq").getAsString();
				RFQ_DetailVO rfq=gson.fromJson(rfqs, RFQ_DetailVO.class);
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
				CalendarVO cal=new CalendarService().checkForRes(c, format.format(rfq.getSer_date()));
				if(cal==null)
				{
				String servtypeno=new Service_TypeService().getOne(rfq.getStype_no()).getStype_no();
				String content = name + "-" +servtypeno+"服務";
				CalendarVO calendarVO = new CalendarVO();
				calendarVO.setCom_no(c);
				calendarVO.setContent(content);
				calendarVO.setCal_date(rfq.getSer_date());
				rfq.setStatus("0");
				new RFQ_DetailService().updateRFQDetailStatus(rfq.getStatus(),rfq.getRfqdetail_no());
				new ReservationService().addReservation(menno, c, new Timestamp(System.currentTimeMillis()), rfq.getSer_date(), q.getQuo_no(), rfq.getStype_no(), q.getPrice(), "1", calendarVO, rfq);
				outStr="OK";
				}else {
				 outStr="no";
				}
			}else if(action.equals("getallheartyvo"))
			{
				String no=jsonObject.get("no").getAsString();
				List<ComTraVO> com=new ComTraService().getComTraByMemNo(no);
				List<ComVO> cc=new ArrayList<>();
				for(ComTraVO c: com)
				{
					cc.add(new ComService().getOneCom(c.getCom_no()));
				}
				outStr=gson.toJson(cc);
				
			}else if(action.equals("heartyfmylike"))
			{
				String no=jsonObject.get("no").getAsString();
				List<ServVO> ser=new ServService().getCom(no);
				outStr=gson.toJson(ser);
			}else if(action.equals("deletemyherty"))
			{
				String no=jsonObject.get("no").getAsString();
				String men=jsonObject.get("men").getAsString();
				ComTraVO cc=new ComTraService().getComTraByComNoAndMemNo(no, men);
				new  ComTraService().deleteComTra(cc.getComtra_no());
				System.out.println(no+"  "+cc.getCom_no());
			}else if(action.equals("findifadd"))
			{
				String no=jsonObject.get("com").getAsString();
				String men=jsonObject.get("men").getAsString();
				ComTraVO cc=new ComTraService().getComTraByComNoAndMemNo(no, men);
				System.out.println(cc);
				if(cc!=null)
				{
					outStr="no";
				}else
				{
					outStr="";
				}
				System.out.println(outStr);
			}else if(action.equals("addnewhearty"))
			{
				String no=jsonObject.get("com").getAsString();
				String men=jsonObject.get("men").getAsString();
				new ComTraService().addComTra(no, men, new Timestamp(System.currentTimeMillis()));
			}else if(action.equals("updatepicture"))
			{
				String no=jsonObject.get("no").getAsString();
				String picture=jsonObject.get("picture").getAsString();
				byte[] b = Base64.getMimeDecoder().decode(picture);
				AlbumVO a=new AlbumService().addAlbum(no, "手機拍照", b, new Timestamp(System.currentTimeMillis()));
	            new ContentService().addContent(a.getAlb_no(), a.getCreate_date(), a.getCover(), null);
			}
		
		
		
			
		
	    }
		
        rs.setContentType(CONTENT_TYPE);	
		PrintWriter out = rs.getWriter();
		out.write(outStr);
	}

	
	
	protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		
		doPost(rq,rs);
		
	}

}
