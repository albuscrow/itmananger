package com.itmanapp.entity;

import com.google.gson.JsonObject;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检设备实体类
 * 
 */
public class DeviceNeedToCheck {

	public class Info{
	  Long tmrId;
      String tmrName;
      String tmrCode;
      String tmrAddress;
      Long tmrWordsId;
      Long tmrAreaId;
      Long tmrUnitId;
      Long tmrDepId;
      String tmrPosition;
      Long tmrManager;
      Long tmrUnitManager;
      String tmrDescription;
      Long tmrAddPerson;
      Long tmrAddDate;
      Integer tmrStatus;
      String unitManager;
      String unitManagerPhone;
      String roomManager;
      String roomManagerPhone;

  
		
		
		
	 /**
	 * @return the tmrId
	 */
	public Long getTmrId() {
		return tmrId;
	}
	/**
	 * @param tmrId the tmrId to set
	 */
	public void setTmrId(Long tmrId) {
		this.tmrId = tmrId;
	}
	/**
	 * @return the tmrName
	 */
	public String getTmrName() {
		return tmrName;
	}
	/**
	 * @param tmrName the tmrName to set
	 */
	public void setTmrName(String tmrName) {
		this.tmrName = tmrName;
	}
	/**
	 * @return the tmrCode
	 */
	public String getTmrCode() {
		return tmrCode;
	}
	/**
	 * @param tmrCode the tmrCode to set
	 */
	public void setTmrCode(String tmrCode) {
		this.tmrCode = tmrCode;
	}
	/**
	 * @return the tmrAddress
	 */
	public String getTmrAddress() {
		return tmrAddress;
	}
	/**
	 * @param tmrAddress the tmrAddress to set
	 */
	public void setTmrAddress(String tmrAddress) {
		this.tmrAddress = tmrAddress;
	}
	/**
	 * @return the tmrWordsId
	 */
	public Long getTmrWordsId() {
		return tmrWordsId;
	}
	/**
	 * @param tmrWordsId the tmrWordsId to set
	 */
	public void setTmrWordsId(Long tmrWordsId) {
		this.tmrWordsId = tmrWordsId;
	}
	/**
	 * @return the tmrAreaId
	 */
	public Long getTmrAreaId() {
		return tmrAreaId;
	}
	/**
	 * @param tmrAreaId the tmrAreaId to set
	 */
	public void setTmrAreaId(Long tmrAreaId) {
		this.tmrAreaId = tmrAreaId;
	}
	/**
	 * @return the tmrUnitId
	 */
	public Long getTmrUnitId() {
		return tmrUnitId;
	}
	/**
	 * @param tmrUnitId the tmrUnitId to set
	 */
	public void setTmrUnitId(Long tmrUnitId) {
		this.tmrUnitId = tmrUnitId;
	}
	/**
	 * @return the tmrDepId
	 */
	public Long getTmrDepId() {
		return tmrDepId;
	}
	/**
	 * @param tmrDepId the tmrDepId to set
	 */
	public void setTmrDepId(Long tmrDepId) {
		this.tmrDepId = tmrDepId;
	}
	/**
	 * @return the tmrPosition
	 */
	public String getTmrPosition() {
		return tmrPosition;
	}
	/**
	 * @param tmrPosition the tmrPosition to set
	 */
	public void setTmrPosition(String tmrPosition) {
		this.tmrPosition = tmrPosition;
	}
	/**
	 * @return the tmrManager
	 */
	public Long getTmrManager() {
		return tmrManager;
	}
	/**
	 * @param tmrManager the tmrManager to set
	 */
	public void setTmrManager(Long tmrManager) {
		this.tmrManager = tmrManager;
	}
	/**
	 * @return the tmrUnitManager
	 */
	public Long getTmrUnitManager() {
		return tmrUnitManager;
	}
	/**
	 * @param tmrUnitManager the tmrUnitManager to set
	 */
	public void setTmrUnitManager(Long tmrUnitManager) {
		this.tmrUnitManager = tmrUnitManager;
	}
	/**
	 * @return the tmrDescription
	 */
	public String getTmrDescription() {
		return tmrDescription;
	}
	/**
	 * @param tmrDescription the tmrDescription to set
	 */
	public void setTmrDescription(String tmrDescription) {
		this.tmrDescription = tmrDescription;
	}
	/**
	 * @return the tmrAddPerson
	 */
	public Long getTmrAddPerson() {
		return tmrAddPerson;
	}
	/**
	 * @param tmrAddPerson the tmrAddPerson to set
	 */
	public void setTmrAddPerson(Long tmrAddPerson) {
		this.tmrAddPerson = tmrAddPerson;
	}
	/**
	 * @return the tmrAddDate
	 */
	public Long getTmrAddDate() {
		return tmrAddDate;
	}
	/**
	 * @param tmrAddDate the tmrAddDate to set
	 */
	public void setTmrAddDate(Long tmrAddDate) {
		this.tmrAddDate = tmrAddDate;
	}
	/**
	 * @return the tmrStatus
	 */
	public Integer getTmrStatus() {
		return tmrStatus;
	}
	/**
	 * @param tmrStatus the tmrStatus to set
	 */
	public void setTmrStatus(Integer tmrStatus) {
		this.tmrStatus = tmrStatus;
	}
	/**
	 * @return the unitManager
	 */
	public String getUnitManager() {
		return unitManager;
	}
	/**
	 * @param unitManager the unitManager to set
	 */
	public void setUnitManager(String unitManager) {
		this.unitManager = unitManager;
	}
	/**
	 * @return the unitManagerPhone
	 */
	public String getUnitManagerPhone() {
		return unitManagerPhone;
	}
	/**
	 * @param unitManagerPhone the unitManagerPhone to set
	 */
	public void setUnitManagerPhone(String unitManagerPhone) {
		this.unitManagerPhone = unitManagerPhone;
	}
	/**
	 * @return the roomManager
	 */
	public String getRoomManager() {
		return roomManager;
	}
	/**
	 * @param roomManager the roomManager to set
	 */
	public void setRoomManager(String roomManager) {
		this.roomManager = roomManager;
	}
	/**
	 * @return the roomManagerPhone
	 */
	public String getRoomManagerPhone() {
		return roomManagerPhone;
	}
	/**
	 * @param roomManagerPhone the roomManagerPhone to set
	 */
	public void setRoomManagerPhone(String roomManagerPhone) {
		this.roomManagerPhone = roomManagerPhone;
	}
	/**
	 * @return the tcId
	 */
	public Long getTcId() {
		return tcId;
	}
	/**
	 * @param tcId the tcId to set
	 */
	public void setTcId(Long tcId) {
		this.tcId = tcId;
	}
	/**
	 * @return the tcName
	 */
	public String getTcName() {
		return tcName;
	}
	/**
	 * @param tcName the tcName to set
	 */
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	/**
	 * @return the tcCode
	 */
	public String getTcCode() {
		return tcCode;
	}
	/**
	 * @param tcCode the tcCode to set
	 */
	public void setTcCode(String tcCode) {
		this.tcCode = tcCode;
	}
	/**
	 * @return the tcUnitId
	 */
	public Long getTcUnitId() {
		return tcUnitId;
	}
	/**
	 * @param tcUnitId the tcUnitId to set
	 */
	public void setTcUnitId(Long tcUnitId) {
		this.tcUnitId = tcUnitId;
	}
	/**
	 * @return the tcDepId
	 */
	public Long getTcDepId() {
		return tcDepId;
	}
	/**
	 * @param tcDepId the tcDepId to set
	 */
	public void setTcDepId(Long tcDepId) {
		this.tcDepId = tcDepId;
	}
	/**
	 * @return the tcRoomId
	 */
	public Long getTcRoomId() {
		return tcRoomId;
	}
	/**
	 * @param tcRoomId the tcRoomId to set
	 */
	public void setTcRoomId(Long tcRoomId) {
		this.tcRoomId = tcRoomId;
	}
	/**
	 * @return the tcLayDate
	 */
	public String getTcLayDate() {
		return tcLayDate;
	}
	/**
	 * @param tcLayDate the tcLayDate to set
	 */
	public void setTcLayDate(String tcLayDate) {
		this.tcLayDate = tcLayDate;
	}
	/**
	 * @return the tcPosition
	 */
	public String getTcPosition() {
		return tcPosition;
	}
	/**
	 * @param tcPosition the tcPosition to set
	 */
	public void setTcPosition(String tcPosition) {
		this.tcPosition = tcPosition;
	}
	/**
	 * @return the tcDescription
	 */
	public String getTcDescription() {
		return tcDescription;
	}
	/**
	 * @param tcDescription the tcDescription to set
	 */
	public void setTcDescription(String tcDescription) {
		this.tcDescription = tcDescription;
	}
	/**
	 * @return the tcAddPerson
	 */
	public Long getTcAddPerson() {
		return tcAddPerson;
	}
	/**
	 * @param tcAddPerson the tcAddPerson to set
	 */
	public void setTcAddPerson(Long tcAddPerson) {
		this.tcAddPerson = tcAddPerson;
	}
	/**
	 * @return the tcAddDate
	 */
	public Long getTcAddDate() {
		return tcAddDate;
	}
	/**
	 * @param tcAddDate the tcAddDate to set
	 */
	public void setTcAddDate(Long tcAddDate) {
		this.tcAddDate = tcAddDate;
	}
	/**
	 * @return the tcStatus
	 */
	public Integer getTcStatus() {
		return tcStatus;
	}
	/**
	 * @param tcStatus the tcStatus to set
	 */
	public void setTcStatus(Integer tcStatus) {
		this.tcStatus = tcStatus;
	}
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}
	/**
	 * @param depName the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}
	Long tcId;
     String tcName;
     String tcCode;
     Long tcUnitId;
     Long tcDepId;
     Long tcRoomId;
     String tcLayDate;
     String tcPosition;
     String tcDescription;
     Long tcAddPerson;
     Long tcAddDate;
     Integer tcStatus;
     String roomName;
     String unitName;
     String depName;
     
		public		Long tdId;
		public		String tdCode;
		public		String tdName;
		public		Long tdUnitId;
		public		Long tdDepId;
		public		Long tdRoomId;
		public		Long tdCabinetId;
		public		Long tdSupplyId;
		public		String tdModel;
		public		Integer tdType;
		public		Long tdAddPerson;
		public		Long tdAddDate;
		public		Integer tdStatus;
		public		String tdPostion;
		public		String tdBuyDate;
		public		Long tdWarranty;
		public		String tdDescription;
		public		Integer tdOutStatus;
		/**
		 * @return the tdId
		 */
		public Long getTdId() {
			return tdId;
		}
		/**
		 * @param tdId the tdId to set
		 */
		public void setTdId(Long tdId) {
			this.tdId = tdId;
		}
		/**
		 * @return the tdCode
		 */
		public String getTdCode() {
			return tdCode;
		}
		/**
		 * @param tdCode the tdCode to set
		 */
		public void setTdCode(String tdCode) {
			this.tdCode = tdCode;
		}
		/**
		 * @return the tdName
		 */
		public String getTdName() {
			return tdName;
		}
		/**
		 * @param tdName the tdName to set
		 */
		public void setTdName(String tdName) {
			this.tdName = tdName;
		}
		/**
		 * @return the tdUnitId
		 */
		public Long getTdUnitId() {
			return tdUnitId;
		}
		/**
		 * @param tdUnitId the tdUnitId to set
		 */
		public void setTdUnitId(Long tdUnitId) {
			this.tdUnitId = tdUnitId;
		}
		/**
		 * @return the tdDepId
		 */
		public Long getTdDepId() {
			return tdDepId;
		}
		/**
		 * @param tdDepId the tdDepId to set
		 */
		public void setTdDepId(Long tdDepId) {
			this.tdDepId = tdDepId;
		}
		/**
		 * @return the tdRoomId
		 */
		public Long getTdRoomId() {
			return tdRoomId;
		}
		/**
		 * @param tdRoomId the tdRoomId to set
		 */
		public void setTdRoomId(Long tdRoomId) {
			this.tdRoomId = tdRoomId;
		}
		/**
		 * @return the tdCabinetId
		 */
		public Long getTdCabinetId() {
			return tdCabinetId;
		}
		/**
		 * @param tdCabinetId the tdCabinetId to set
		 */
		public void setTdCabinetId(Long tdCabinetId) {
			this.tdCabinetId = tdCabinetId;
		}
		/**
		 * @return the tdSupplyId
		 */
		public Long getTdSupplyId() {
			return tdSupplyId;
		}
		/**
		 * @param tdSupplyId the tdSupplyId to set
		 */
		public void setTdSupplyId(Long tdSupplyId) {
			this.tdSupplyId = tdSupplyId;
		}
		/**
		 * @return the tdModel
		 */
		public String getTdModel() {
			return tdModel;
		}
		/**
		 * @param tdModel the tdModel to set
		 */
		public void setTdModel(String tdModel) {
			this.tdModel = tdModel;
		}
		/**
		 * @return the tdType
		 */
		public Integer getTdType() {
			return tdType;
		}
		/**
		 * @param tdType the tdType to set
		 */
		public void setTdType(Integer tdType) {
			this.tdType = tdType;
		}
		/**
		 * @return the tdAddPerson
		 */
		public Long getTdAddPerson() {
			return tdAddPerson;
		}
		/**
		 * @param tdAddPerson the tdAddPerson to set
		 */
		public void setTdAddPerson(Long tdAddPerson) {
			this.tdAddPerson = tdAddPerson;
		}
		/**
		 * @return the tdAddDate
		 */
		public Long getTdAddDate() {
			return tdAddDate;
		}
		/**
		 * @param tdAddDate the tdAddDate to set
		 */
		public void setTdAddDate(Long tdAddDate) {
			this.tdAddDate = tdAddDate;
		}
		/**
		 * @return the tdStatus
		 */
		public Integer getTdStatus() {
			return tdStatus;
		}
		/**
		 * @param tdStatus the tdStatus to set
		 */
		public void setTdStatus(Integer tdStatus) {
			this.tdStatus = tdStatus;
		}
		/**
		 * @return the tdPostion
		 */
		public String getTdPostion() {
			return tdPostion;
		}
		/**
		 * @param tdPostion the tdPostion to set
		 */
		public void setTdPostion(String tdPostion) {
			this.tdPostion = tdPostion;
		}
		/**
		 * @return the tdBuyDate
		 */
		public String getTdBuyDate() {
			return tdBuyDate;
		}
		/**
		 * @param tdBuyDate the tdBuyDate to set
		 */
		public void setTdBuyDate(String tdBuyDate) {
			this.tdBuyDate = tdBuyDate;
		}
		/**
		 * @return the tdWarranty
		 */
		public Long getTdWarranty() {
			return tdWarranty;
		}
		/**
		 * @param tdWarranty the tdWarranty to set
		 */
		public void setTdWarranty(Long tdWarranty) {
			this.tdWarranty = tdWarranty;
		}
		/**
		 * @return the tdDescription
		 */
		public String getTdDescription() {
			return tdDescription;
		}
		/**
		 * @param tdDescription the tdDescription to set
		 */
		public void setTdDescription(String tdDescription) {
			this.tdDescription = tdDescription;
		}
		/**
		 * @return the tdOutStatus
		 */
		public Integer getTdOutStatus() {
			return tdOutStatus;
		}
		/**
		 * @param tdOutStatus the tdOutStatus to set
		 */
		public void setTdOutStatus(Integer tdOutStatus) {
			this.tdOutStatus = tdOutStatus;
		}
		
	}
      String dname;
      String dcode;


    
    
	/**
	 * @return the dname
	 */
	public String getDname() {
		return dname;
	}
	/**
	 * @param dname the dname to set
	 */
	public void setDname(String dname) {
		this.dname = dname;
	}
	/**
	 * @return the dcode
	 */
	public String getDcode() {
		return dcode;
	}
	/**
	 * @param dcode the dcode to set
	 */
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	
    String type;
    Long typeId;
    String typeName;
    Info info;
    String planName;
	
	/**
	 * @return the planName
	 */
	public String getPlanName() {
		return planName;
	}
	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	long txrId;
    String tdName;
    String tdCode;
    int txrStatus;
    
    String txpName;
    String planDate;
    String tdPosition;
    String tmrName;
    String tcName;
    String txcName;
    long txcId;
    String txpNames;
    
    String addDate;
	
	/**
	 * @return the addDate
	 */
	public String getAddDate() {
		return addDate;
	}
	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	/**
	 * @return the txpName
	 */
	public String getTxpName() {
		return txpName;
	}
	/**
	 * @param txpName the txpName to set
	 */
	public void setTxpName(String txpName) {
		this.txpName = txpName;
	}
	/**
	 * @return the planDate
	 */
	public String getPlanDate() {
		return planDate;
	}
	/**
	 * @param planDate the planDate to set
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	/**
	 * @return the tdPosition
	 */
	public String getTdPosition() {
		return tdPosition;
	}
	/**
	 * @param tdPosition the tdPosition to set
	 */
	public void setTdPosition(String tdPosition) {
		this.tdPosition = tdPosition;
	}
	/**
	 * @return the tmrName
	 */
	public String getTmrName() {
		return tmrName;
	}
	/**
	 * @param tmrName the tmrName to set
	 */
	public void setTmrName(String tmrName) {
		this.tmrName = tmrName;
	}
	/**
	 * @return the tcName
	 */
	public String getTcName() {
		return tcName;
	}
	/**
	 * @param tcName the tcName to set
	 */
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	/**
	 * @return the txcName
	 */
	public String getTxcName() {
		return txcName;
	}
	/**
	 * @param txcName the txcName to set
	 */
	public void setTxcName(String txcName) {
		this.txcName = txcName;
	}
	/**
	 * @return the txcId
	 */
	public long getTxcId() {
		return txcId;
	}
	/**
	 * @param txcId the txcId to set
	 */
	public void setTxcId(long txcId) {
		this.txcId = txcId;
	}
	/**
	 * @return the txpNames
	 */
	public String getTxpNames() {
		return txpNames;
	}
	/**
	 * @param txpNames the txpNames to set
	 */
	public void setTxpNames(String txpNames) {
		this.txpNames = txpNames;
	}
	/**
	 * @return the txrId
	 */
	public long getTxrId() {
		return txrId;
	}
	/**
	 * @param txrId the txrId to set
	 */
	public void setTxrId(long txrId) {
		this.txrId = txrId;
	}
	/**
	 * @return the tdName
	 */
	public String getTdName() {
		return tdName;
	}
	/**
	 * @param tdName the tdName to set
	 */
	public void setTdName(String tdName) {
		this.tdName = tdName;
	}
	/**
	 * @return the tdCode
	 */
	public String getTdCode() {
		return tdCode;
	}
	/**
	 * @param tdCode the tdCode to set
	 */
	public void setTdCode(String tdCode) {
		this.tdCode = tdCode;
	}
	/**
	 * @return the txrStatus
	 */
	public int getTxrStatus() {
		return txrStatus;
	}
	/**
	 * @param txrStatus the txrStatus to set
	 */
	public void setTxrStatus(int txrStatus) {
		this.txrStatus = txrStatus;
	}
	/**
	 * @return the type
	 */
	public int getTypeInt() {
		try {
			return Integer.parseInt(type);
		} catch (Exception e) {
			return 1;
		}
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the info
	 */
	public Info getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(Info info) {
		this.info = info;
	}
	
//	/**巡检系统id*/
//	private int axpId; 
//	/**巡检系统计划名称*/
//	private String planName; 
//	/**状态*/
//	private int status; 
//	/**设备编码*/
//	private String adCode; 
//	/**巡检类型*/
//	private int type; 
//	/***/
//	private String userids; 
//	/***/
//	private String desp; 
//	/**设备编码*/
//	private String asCode; 
//	/**设备id*/
//	private int id; 
//	/**巡检时间*/
//	private String axrAddtime; 
//	/***/
//	private String despName; 
//	/***/
//	private String userNames; 
//	/**巡检系统*/
//	private String asName; 
//	/***/
//	private int destId; 
//	/**巡检目标*/
//	private String adName;
//	public int getAxpId() {
//		return axpId;
//	}
//	public void setAxpId(int axpId) {
//		this.axpId = axpId;
//	}
//	public String getPlanName() {
//		return planName;
//	}
//	public void setPlanName(String planName) {
//		this.planName = planName;
//	}
//	public int getStatus() {
//		return status;
//	}
//	public void setStatus(int status) {
//		this.status = status;
//	}
//	public String getAdCode() {
//		return adCode;
//	}
//	public void setAdCode(String adCode) {
//		this.adCode = adCode;
//	}
//	public int getType() {
//		return type;
//	}
//	public void setType(int type) {
//		this.type = type;
//	}
//	public String getUserids() {
//		return userids;
//	}
//	public void setUserids(String userids) {
//		this.userids = userids;
//	}
//	public String getDesp() {
//		return desp;
//	}
//	public void setDesp(String desp) {
//		this.desp = desp;
//	}
//	public String getAsCode() {
//		return asCode;
//	}
//	public void setAsCode(String asCode) {
//		this.asCode = asCode;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getAxrAddtime() {
//		return axrAddtime;
//	}
//	public void setAxrAddtime(String axrAddtime) {
//		this.axrAddtime = axrAddtime;
//	}
//	public String getDespName() {
//		return despName;
//	}
//	public void setDespName(String despName) {
//		this.despName = despName;
//	}
//	public String getUserNames() {
//		return userNames;
//	}
//	public void setUserNames(String userNames) {
//		this.userNames = userNames;
//	}
//	public String getAsName() {
//		return asName;
//	}
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
//	public int getDestId() {
//		return destId;
//	}
//	public void setDestId(int destId) {
//		this.destId = destId;
//	}
//	public String getAdName() {
//		return adName;
//	}
//	public void setAdName(String adName) {
//		this.adName = adName;
//	}
//	@Override
//	public String toString() {
//		return "PendingInspectionEquipmentEntity [axpId=" + axpId
//				+ ", planName=" + planName + ", status=" + status + ", adCode="
//				+ adCode + ", type=" + type + ", userids=" + userids
//				+ ", desp=" + desp + ", asCode=" + asCode + ", id=" + id
//				+ ", axrAddtime=" + axrAddtime + ", despName=" + despName
//				+ ", userNames=" + userNames + ", asName=" + asName
//				+ ", destId=" + destId + ", adName=" + adName + "]";
//	} 

}
