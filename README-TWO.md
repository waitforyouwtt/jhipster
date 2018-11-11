/**《遮天》人员概况表*/
entity UserInfo {
	id Integer,
    /**用户ID*/
    userId String,
    /**人物名字*/
	userName String,
    /**人物昵称*/
	nickName String,
    /**性别*/
    sex Sex,
    /**归属门派*/
    ascription Ascription,
    /**家庭住址*/
    address String,
	/**电子邮箱*/
    email String,
	/**手机号*/
    phoneNumber String,
	/**生日*/
    birthday Instant,
    /**创建时间*/
    createTime Instant,
    /**修改时间*/
    updateTime Instant
}
/**银行表*/
entity BankInfo{
   id Integer,
   /**记录Id*/
   bankId String,
   /**用户Id*/
   userId String,
   /**开户账号*/
   account String,
   /**开户名*/
   accountName String,
   /**开户银行*/
   bankName String,
   /**银行支行*/
   bankBranchName String,
   /**存款金额*/
   amount BigDecimal,
   /**创建时间*/
   createTime Instant,
   /**修改时间*/
   updateTime Instant
}
paginate UserInfo,BankInfo with infinite-scroll
paginate UserInfo,BankInfo with pagination

service all with serviceImpl
/**人物性别*/
enum Sex{
   MAN,
   WOMAN
}
/**归属门派*/
enum Ascription{
   SHENGTI,
   YAOGUANG,
   JIANGJIA,
   YAOZU,
   SANXIU
}

