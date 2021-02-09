package com.zjj.Test;


import com.alibaba.fastjson.JSONObject;
import com.zjj.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/help")
public class HelpWeb {
    private static final Logger logger = LoggerFactory.getLogger(HelpWeb.class);

    @GetMapping("/common")
    public Result getCommon() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "### 统一平台有几种预约方式？\n\n统一平台包括网上预约 www.114yygh.com 和114电话预约两种预约方式。\n\n### 我是第一次使用这个平台预约，是否能够不注册就使用预约挂号?\n\n首次使用预约挂号，需要进行注册才能够使用预约挂号服务，可登录统一平台网站www.114yygh.com 注册。\n\n### 注册时填写手机号码有什么用？\n\n注册时填写的手机号码作为修改信息环节、预约环节用于验证和接收信息的必要工具，并且当医生临时停诊或发布重要通知时，以便我们能够及时通知到您。\n\n### 北京市预约挂号统一平台可以使用哪些证件登记注册？\n\n目前可使用居民身份证（包含现役军人、人民武装警察）、港澳居民居住证、台湾居民居住证、港澳居民来往内地通行证、台湾居民来往大陆通行证、外国人永久居留身份证、护照。\n\n### 无身份证号，或者已经申报户口但还未办理身份证的患儿如何注册预约？\n\n对于没有身份证号的患儿，请直接去现场儿童急门诊挂号就诊。\n对于已经申报户口但还未办理身份证的患儿，可凭身份证号在统一平台上正常注册预约，并填写监护人的实名身份信息。就诊时凭带有身份证号码相关联的有效证件（如户口本）取号就诊，或者直接去现场儿童急门诊挂号就诊。\n\n### 没有统一平台支持的有效证件如何注册预约？\n\n没有统一平台支持的有效证件的用户，目前不能通过统一平台网站和114电话注册预约，只能到医院现场挂号就诊。\n\n### 要患者的姓名和身份证件有什么用？\n\n患者身份证件和患者信息是取号就诊的必要凭证。\n为规范号源管理，预约挂号系统采用\"实名制\"挂号，用户在通过统一平台注册预约时，须提供\"患者本人\"的有效证件号、患者姓名和\"预约手机号码\"等信息。系统根据用户填写的\"预约手机号\"将预约成功通知短信和预约识别码发送到手机上。\n患者到医院取号就诊时，须出示患者本人的有效证件和预约成功短信，医务人员会对照计算机系统核对患者预约信息，验证是否与在该院的就诊信息中的姓名、手机号或身份证号一致，若出现任何信息不符，都会导致计算机系统无法正确找到病人的预约信息，导致取号失败。\n\n### 患者注册信息填写有误，是否会影响就诊？\n\n114电话和网络注册及预约都需实名制患者信息，就诊当天也是需要凭患者真实有效证件取号，若信息不正确将影响当天取号就诊。所以建议用户发现注册信息有误时，请尽快提交申请处理，如果您已经预约，建议您在预约取消截止时限内，先取消预约订单，再重新注册账号。或者走账号申诉流程让工作人员协助修改。\n\n### 预约成功，发现注册信息有误，我能修改么？\n\n不能直接修改，要解决此类问题，只能把原有的预约号在规定取消时限内取消掉，然后请关注“北京114预约挂号”微信公众号按流程填写申诉信息，我们会在5个工作日内处理完成，申诉结果会以短信形式发送给申诉信息中的手机号。\n\n### 114电话预约和网站预约的号源信息是否同步？\n\n是同步的，二者属于同一系统，同一个大号池，不同的预约方式；考虑到老人挂号方便， 电话预约的号源放号比例会相对多一些，所以在部分号源紧张的情况下，建议两种预约方式都尝试一下。\n\n### 网站上显示有号，可填完资料提交时却告诉我没号了？\n\n遇到这种情况，是因为有很多用户在同时预约此号，该号源在您点击\"预约确认\"之前已被其他用户先预约成功导致的，您可选择其他医生或者改期预约。\n\n### 为什么找不到我要挂的专家号？\n\n根据北京市卫生和计划生育委员会医改新政策按医院、科室、职称、专业不点名预约。对于取消点名挂号，我们强调的是目前北京市实行的取消点名挂号只是针对首诊病人，也就是\"首诊取消点名挂号\"。因为对于第一次就诊的患者，由于对就诊流程和医生诊疗特点不太熟悉，往往会盲目选择医生和扎堆儿挑专家，从而加剧了看专家难。而对于需要定期复诊的老病号，是由首诊医生直接为患者预约下次就诊号源，也就是复诊可以点名，这个点名过程是由接诊专家为患者复诊预约来实现的，从而确保患者就医的连续性。\n\n### 为什么我无法预约？\n\n**原因一：** 请确认是否已正常注册/登录。\n**原因二：** 请确认您是否按网上预约流程正常操作，如方便亦可致电114协助解决。\n**原因三：** 请确认您是否已经违反统一平台规定（恶意挂号和取消、多次无故不取号就诊也不取消、贩卖号源行为等），目前已进入黑名单或者是爽约用户，该类用户将被限制使用预约挂号服务。\n**原因四：** 114电话注册用户首先需要通过网上注册认证，才能正常在网上预约挂号，请确保您是否已经通过认证。\n**原因五：** 为了防止号贩子倒号行为，满足绝大多数百姓的最大利益和公平性，统一平台采取了预约挂号限制。按照北京市卫生和计划生育委员会统一平台提供的预约挂号服务公约，做出如下限制：\n1.同一自然日，同一医院，同一科室，同一就诊单元，同一就诊人，可以预约最多1个号源（就诊单元分为上午、下午、晚上、全天）；\n2. 同一自然日，同一就诊单元，同一就诊人，不同医院，可以预约最多3个号源（就诊单元分为上午、下午、晚上、全天）；\n3. 同一自然日，同一就诊人，可以预约最多4个号源；\n4. 同一自然周（周一到周日），同一就诊人，可以预约最多14个号源；\n5. 同一自然月，同一就诊人，可以预约最多20个号源；\n6. 同一自然季度内，同一就诊人，可以预约最多30个号源。\n7. 同一自然日，同一就诊人，最多取消2个号源；\n\n因患者个人原因主动退号，所退号源仍占用挂号名额；因医生停诊、系统问题等导致等退号，不占用可挂号的配额。\n**原因六：** 平台短信系统繁忙，造成短信验证码发送延迟，请稍后再试，或者亦可通过114电话人工协助下单预约挂号。\n\n### 应该提前多少天预约？\n\n用户一般可预约次日起至三个月内的就诊号源，但由于各医院规定不同，具体预约挂号周期，以114电话查询和网站公示为准。所有医院均不提供当天的预约服务，建议您尽量提前预约。\n\n### 预约挂号统一平台什么时候放号，24小时开放么？\n\n统一平台上各家医院的放号时间有所不同，具体以各家医院预约须知为准。统一平台是24小时开放的，只要上面有预约号，用户就可以自行操作预约。\n\n### 节假日及周六日能预约挂号吗？\n\n节假日不安排预约号，但个别医院周六日是有号源的，具体按各家医院预约须知规定执行。\n\n### 北京市预约挂号统一平台网站预约和114电话预约服务是否收费？\n\n依据北京市政府新政，北京市预约挂号统一平台为用户提供免费注册预约，不收取任何费用。各家医院仅收取与医生职称相对应的医事服务费，如与您预约时不符，则以当日医院挂号窗口为准。\n\n### 为什么我老是收不到验证短信？\n\n北京市预约挂号统一平台验证短信是要在网页上触发后才会下发到用户注册手上，有时候短信系统繁忙等原因，也会导致短信下发延迟。另外，还有一些是由于运营商或者用户自己手机问题引起的，例如：\n1. 用户长时间关机或者不在服务区等导致的短信超时没有递交到用户手机上；\n2. 处于停机状态，呼叫被禁止，用户的语音功能被关闭造成短信功能也一起停用；\n3. 黑名单用户或非法用户；\n4. 确认手机是否被手机系统设置自动拦截，被误认为\"垃圾短信\"；\n5. 手机内存满；\n6. 注册手机号码错误或者号码无效；\n7. 联通或移动用户帐户数据异常；\n\n### 如何知道已预约成功？\n\n确认预约后，就诊人的预约注册手机会同步收到“预约成功短信”以示成功，同时您可以通过\"个人中心>我的账户>挂号订单\"选择对应就诊人查询订单状态。\n预约成功短信内容示例如下：\"xxx您好，您已成功预约xx医院xx科室xx医生，预约识别码[xxxxxxxx]，就诊日期xx年xx月xx日（上午/下午），取号时间：xx点xx分取号，取号地点：xxxxxxxx。\"\n由于\"预约成功通知短信\"包括\"订单详情\"、\"取号时间\"、\"取号地点\"等信息，内容较多，系统将根据内容长短，自动分为1至2或3条发送至您的手机，收到其中的任意一条，都代表您已预约成功。\n请您妥善保存短信，如您未收到短信或者丢失短信，请与114电话联系，人工核实信息后，系统会重新为您发送短信。\n\n### 爽约的判定以及处罚？\n\n爽约是指用户未按医院规定的取号时间提前到医院指定的挂号窗口取号，且超过预约就诊时间未能按时就诊即视为爽约，该预约号源自动作废。如仍需就诊可重新预约或到医院挂号窗口挂号。一年内（自然年）无故爽约累计达到3次的爽约用户将自动进入系统爽约名单，此后3个月内将取消其预约挂号资格。\n\n### 爽约记录的查询和申诉？\n\n用户在网站、微信服务号中，可以点击\"个人中心-挂号订单”选择对应就诊人查询订单信息，部分医院的挂号订单会展现爽约状态（因为平台与每家医院连接方式不尽相同，获取的数据有限）。如您有任何疑问或不解请关注“北京114预约挂号”微信公众号按流程填写申诉信息，工作人员会尽快受理。\n\n### 预约挂号成功后，可以改号吗？\n\n用户应在预约前确定好自己想挂的科室或专家，已成功预约的订单若需要更改，可在规定的取消时限内先做取消预约处理，再重新预约。\n\n### 预约号能转让吗？\n\n不能，预约挂号实行实名制，预约号只供本人使用。\n\n### 预约挂号成功后，临时去不了，能否取消预约？\n\n用户在医院规定的取消时限内可以取消预约号，超过退号截止时间系统将限制用户退号，一般截止时间为就诊日期的前一天下午15:00点，就诊当天不能取消当日预约号。（注：个别医院的预约取消截止时间有所不同，具体规则请关注各家医院的个性化预约须知）\n\n### 没有及时取消预约，也不按时取号就诊，会产生什么影响？\n\n由于号源比较紧张，如您不及时取消预约，也不按时取号就诊，则会视为爽约，系统会记入诚信记录，今后将会影响到您使用预约挂号服务。详见\"爽约规则\"。\n\n### 什么是挂号、退号截止时间？\n\n医院需要将患者的预约记录提前收集导入HIS系统里，便于就诊当日现场的有序就诊。用户必须在截止时间前使用预约挂号服务或进行退号操作，超出预约截止时间，系统将限制操作。具体截止时间各医院不同，为保证您顺利的预约或取消预约号，请尽量提早一天进行操作，具体时间请参见各医院须知。一旦过了医院指定的取号、取消时间，无论医院还是114平台都已无权限帮您取号或取消，希望您的理解。多数医院（见各家医院\"预约须知\"）的取消预约时间限制是以工作日计算，不包含周六日。\n\n### 通过网站或114电话预约后，可以相互查询/取消吗？\n\n可以。在网站预约的可以在114电话取消，反之亦然。\n\n### 挂号成功后但是医师临时停诊了怎么办？\n\n临时停诊是指医生可能因为临时的抢救、保障任务等特殊原因而临时无法出诊的情况，因此不可避免，请患者谅解和配合。遇到此类情况，我们会通过短信或者电话通知到您。\n\n### 注册、修改信息、预约等环节点击发送短信验证码的次数是否有限制？\n\n每个用户每天在预约挂号统一平台上注册、修改信息、预约等环节，点击发送短信验证码的次数是有总体限制的，请合理正常的使用操作，超过限制次数只能次日尝试操作或者拨打114电话预约挂号。\n\n### 预约挂号统一平台对于预约次数有限制么？\n\n为了防止号贩子倒号行为，满足绝大多数百姓的最大利益和公平性，统一平台采取了预约挂号限制。按照北京市卫生和计划生育委员会统一平台提供的预约挂号服务公约，做出如下限制：\n1.同一自然日，同一医院，同一科室，同一就诊单元，同一就诊人，可以预约最多1个号源（就诊单元分为上午、下午、晚上、全天）；\n2. 同一自然日，同一就诊单元，同一就诊人，不同医院，可以预约最多3个号源（就诊单元分为上午、下午、晚上、全天）；\n3. 同一自然日，同一就诊人，可以预约最多4个号源；\n4. 同一自然周（周一到周日），同一就诊人，可以预约最多14个号源；\n5. 同一自然月，同一就诊人，可以预约最多20个号源；\n6. 同一自然季度内，同一就诊人，可以预约最多30个号源。\n7. 同一自然日，同一就诊人，最多取消2个号源；\n\n因患者个人原因主动退号，所退号源仍占用挂号名额；因医生停诊、系统问题等导致等退号，不占用可挂号的配额。\n\n### 预约成功后，如何去医院取号就诊？\n\n成功预约挂号后，系统将自动保存用户预约记录。就诊当天，您需要在医院规定的取号时间之内，前往医院指定的地点取号，并缴纳医院规定的医事服务费，逾期预约自动作废。具体取号时间和取号地点请查阅系统下发的预约成功短信和各家医院的个性化预约须知，或者拨打114人工客服电话协助查询。\n本着方便患者的原则，个别医院开通了自助取号或分诊台先取号后缴费服务，以减少患者就诊的手续，具体取号流程和要求以医院现场公示为准。如患者就诊后未缴费，将被列入欠费名单，您将无法通过统一预约挂号平台或在各医院进行任何形式的预约挂号和窗口挂号。\n取号时需要提供以下重要凭证：\n1. 就诊当日取号时，就诊人需凭就诊人本人注册的有效证件原件、预约成功通知短信和预约识别码至医院指定的挂号窗口取号（个别医院要求先办理就诊卡并关联社保卡后再取号，具体详见各家医院的个性化预约须知）；取号时医务人员将核对就诊者的身份信息和预约记录，如信息验证不符，则医院不能提供相应的诊疗服务。\n\n2.  \"首都医科大学附属北京儿童医院（北京儿童医院）\"和\"首都儿科研究所附属儿童医院（儿研所）\"预约取号比较特殊。\n*  预约\"北京儿童医院\"，就诊当日须出示监护人注册时的有效证件取号就诊。同时，就诊当日必须带患儿就诊，否则此号源作废；\n* 预约\"儿研所\"，就诊当日须出示患儿的有效证件（户口本/身份证/护照/港澳通行证/台胞证），及医保卡或京医通卡取号就诊，无京医通卡患儿可到门诊大厅建卡中心办理京医通卡后持卡取号。\n\n### 预约成功后，到医院还需要交费吗？\n\n成功预约后需到指定医院，由医院收取相应的医事服务费。\n\n### 预约成功后，去医院需要排队吗？\n\n预约成功后，您需要在规定时间内排队取号，具体就诊位数及时间以各医院安排为准。\n\n### 我能不能稍微迟会到医院取号？\n\n如超过医院规定的时间未取号，本次预约号源作废。所以建议用户合理安排自己的时间，按时取号就诊。\n\n### 黑名单用户的判定、处罚及解冻？\n\n判定规则：\n注册环节：身份证号不符合规范；\n取消环节：频繁取消，3个月内频繁取消大于15个号源（不含15个）；\n黑名单用户处罚规则：黑名单用户可以登录、取消挂号，其余任何操作均被限制。\n\n### 临床咨询、疾病咨询、药品咨询、费用咨询等问题？\n\n预约挂号统一平台作为一个数据处理中心，只提供预约挂号的技术支持，临床医学、疾病、药品和医事服务费方面的问题请咨询相关医院。\n\n### 医疗保险问题？\n\n医疗保险方面的问题请咨询用户的投保单位，具体事宜依据当地医疗保险政策执行。\n\n### 预约识别码丢失、短信接收不完整怎办？\n\n预约成功后，注册手机会收到预约成功的一组短信2-3条，请严格按短信内容提示的时间和地点取号就诊即可。\n如确认已经预约成功，短信误删或接收不全，可以使用以下任一方式重新获得短信通知。\n\n1. 拨打010-114，由114预约挂号专席补发约成短信；\n2. 如果用户已经开通\"个人中心\"，则可以登录个人中心，在\"挂号订单\"中查看预约成功的订单及预约识别码。\n\n### 预约挂号时医保卡、就诊卡都是必填项吗？\n\n预约挂号时，需要填写就诊卡、医保卡等信息，但这些信息并非是所有医院预约挂号的必填项目。是否属于必填，可根据编辑框后面对应提示判断，详细预约流程请参见\"网站预约挂号流程\"。\n\n### 是否可以用自己账户为家属预约挂号？\n\n所有医院预约必须是患者实名注册预约。\n北京儿童医院和首都儿科研究所附属儿童医院为儿童预约时，在填写儿童证件等信息基础上必须填写监护人实名信息，具体规定参考这两家医院的\"预约须知\"。"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/register")
    public Result getRegister() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "### 网站预约挂号如何注册？\n**第一步**\n进入北京市预约挂号统一平台网站首页，点击“登录/注册”链接。\n![](http://img.114yygh.com/static/web/help_login_header.png)\n**第二步**\n根据系统提示填写手机号验证码，并输入姓名和证件号码，完成实名认证，提交注册。\n![](http://img.114yygh.com/static/web/help_login.png)\n![](http://img.114yygh.com/static/web/help_auth.png)\n**第三步**\n完注册后可添加就诊人操作挂号。\n![](http://img.114yygh.com/static/web/help_patient_mangement.png)\n**温馨提示**\n\n* 身份证号和姓名必须是患者本人的，要跟身份证上的保持一致。\n* 还有手机号这一项，建议用户正确填写自己的真实有效号码，以便正常的接收平台下发的短信，短信服务通道对以下情况的用户不能提供短信下发服务，具体请查阅《为什么我老是收不到验证码》。\n* 已拨打过010-114电话注册预约的用户，如果要在统一平台网站预约则需要验证手机号并设置密码，即可正常预约挂号。\n\n### 注册预约挂号统一平台有几种方式？\n两种，可通过北京市预约挂号统一平台网站注册和“北京114预约挂号”微信公众号注册。\n### 为什么需要提交实名信息？\n因为挂号预约时，医院需要实名信息与医保卡关联并进行登记，为您的就诊带来方便。同时为抑制倒号行为，保障就诊患者权益，到医院取号时也需要核对患者实名信息的。\n所以，请务必注册您的真实有效信息，否则我们将无法提供服务。\n### 一个手机号可以注册几个用户？\n一个手机号可以注册1个用户，但是可以绑定4个就诊人\n### 没有手机号的用户如何注册？\n没有手机号的用户可以拨打114人工坐席协助注册，注册时可以预留座机号。但以后预约只能通过114电话方式预约，预约成功信息和预约识别码由话务员告知用户，用户自行记录下来。\n### 收不到短信注册码怎么办？\n**方法一：** 确认手机是否被手机系统设置自动拦截，被误认为\"垃圾短信\"。\n**方法二：** 确认注册手机号是否正确。\n**方法三：** 若超过3分钟仍未收到短信注册码，可点击注册页面的\"点击获取\"，再次重发（以最后一次接收到的短信注册码为准）。\n**方法四：** 若再次查收仍未收到短信注册码，更换其他手机号尝试重新注册。\n**方法五：** 拨打114/010114人工客服协助解决。\n### 我的身份证件信息已被注册占用了怎么办？\n如果该账号是您自己注册的，您可以直接使用该账号登录，并核实确认账号信息是否是本人的真实信息；\n如果系统提示\"该身份证件信息已被注册\"，并且确认该账号不是您注册的，您可以申诉，请提供您的真实姓名、联系手机、身份证号码以及本人手持有效身份证件的正面电子版和背面复印件，复印件均为电子版，要求清晰可辨，请选择**申诉**按流程填写信息，我们会在5个工作日内处理完成，申诉结果会以短信形式发送给申诉信息中的手机号。\n### 网站注册用户拨打114电话预约还需要再次注册吗？\n不需要，网站注册和114电话注册信息是互通的。网站注册成功后可直接拨打114电话预约无需再次注册；同样的，114电话注册成功后亦可登录网站进行预约，也无需再次注册，而只需要验证手机号并设置密码，即可正常预约挂号。（注：北京朝阳医院、中日友好医院、北京协和医院、北京大学第一医院、北京儿童医院五家医院除外）\n### 注册失败报错及原因分析？\n有以下四种情况：\n**情况一：** 自己的家人或朋友已经帮忙注册了，自己不知情或忘记了，待进一步了解清楚后直接登录即可，无须再注册。\n**情况二：** 北京市预约挂号统一平台包括网站预约平台和114电话预约平台，网站注册和114电话注册信息是互通的。网站注册成功后可直接拨打114电话预约无需再次注册；\n**情况三：** 注册信息有误，请进一步核实注册信息。\n**情况四：** 手机短信注册码填写错误，短信注册码应为4位数字组成，重复发送以最后一次接收到的短信注册码为准。如还有问题请致电114电话协助解决，或在线留言（跳转到反馈页面）\n### 北京市预约挂号统一平台注册规则？\n注：须以就诊者本人的真实有效信息实名注册。\n**姓名**\n\n* 中文名只能由汉字开头的汉字和\" · \"组成，长度大于等于2个汉字并且小于等于10个汉字。\n* 英文名只能由英文字母开头的英文字母、\" . \"和空格组成，长度大于等于4个字符并且小于等于20个字符。\n\n**证件号码**\n\n* 身份证：18位有效身份证。其中省份、生日、性别必须符合身份证规则。\n* 军官证：大于等于8并且小于等于10个字符。\n* 护照：大于等于7并且小于等于15个字符。\n* 港澳通：大于等于9并且小于等于12个字符。\n* 台胞证：大于等于8并且小于等于11个字符。\n\n**手机号码**\n\n* 11位并且以1开头的数字。\n* 一个手机号只能绑定一个用户注册使用，添加三个就诊人。"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/login")
    public Result getLogin() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "### 如何登录\n**第一步**\n进入北京市预约挂号统一平台网站首页，点击“登录/注册”链接。\n![登录](http://img.114yygh.com/static/web/help_login_header.png)\n**第二步**\n根据系统提示填写手机号、验证码完成登录，若在平台绑定过微信，可点击微信登陆进行登录。\n![验证码](http://img.114yygh.com/static/web/help_login_detail.png)\n**温馨提示**\n关于114电话注册用户首次登录网上预约平台信息认证问题？\n已拨打过010-114电话注册预约的用户，如果通过统一平台网站预约则无需再注册，只需要验证手机号并设置密码，即可正常预约挂号。\n114电话注册用户首次登录网上预约平台，只需验证登录页面验证手机号并设置密码，即可正常预约挂号。\n* 如果您名下存在有效预约号，验证时只能使用原114电话注册的同一手机号码认证注册，否则可选择先取消当前预约或就诊后再验证\n* 原114电话注册座机号的用户，如果用户名下存在预约号则暂不能网上认证，请取消当前预约或就诊后再更换手机号完成验证。"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/account")
    public Result getAccount() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "### 用户注册信息提交后是否还可以修改?\n注册ID的实名信息，需要人工审核后可以修改，请谨慎填写\n### 用户资料发生变更如何修改?\n用户注册成功后，由于用户的有些资料信息发生改变，需要对用户的资料进行修改(譬如手机号码等)，在个人信息修改页面，如原注册手机号有效，用户可选择在线自助修改手机号，修改其它资料信息需要走申诉流程，请关注“北京114预约挂号”微信公众号按流程填写申诉信息。\n用户资料修改注意几下几点：\n\n* 请保证您的统一平台账户信息真实有效，如用错误信息，或虚假信息预约挂号，不仅在就诊当日影响您取号就诊，同时也会影响到您今后的预约行为。\n* 注册用户如名下存在未就诊的预约号源时，不能修改注册信息，请先取消所有当前有效预约号源或等约成号源过期的次日后再申请修改。\n* 用户如修改本人注册姓名、有效证件号、手机号，请关注“北京114预约挂号”微信公众号按流程填写申诉信息，我们会在5个工作日内处理完成，申诉结果会以短信形式发送给申诉信息中的手机号。\n* 统一平台不支持同时修改姓名和证件号码的申请。\n* 如遇\"无法登录\"等特殊情况，请关注“北京114预约挂号”微信公众号按流程填写申诉信息，我们会在5个工作日内处理完成，申诉结果会以短信形式发送给申诉信息中的手机号。\n* 为保证注册用户信息安全，在线申诉时须提供用户本人与证件合照，仅供北京市预约挂号统一平台申诉使用，敬请理解。\n\n### 名字、身份证件号码不小心输错了，手机号换号了或者停机了，怎么办？\n修改个人信息，请关注“北京114预约挂号”微信公众号按流程填写申诉信息，我们会在5个工作日内处理完成，申诉结果会以短信形式发送给申诉信息中的手机号。请您严格按照以上流程操作，确保您的申请得到及时处理，谢谢您的配合。\n### 账号为什么会被冻结？如何申诉\n如果您已经被判定为爽约用户（详见\"爽约的判定及处罚\")或者判定为黑名单用户(详见\"黑名单用户的判定及处罚\"），可能会导致您的账号被冻结。如您有任何疑问或不解可关注“北京114预约挂号”微信公众号按流程填写申诉信息 ，选择申诉理由“其它”，描述您遇到的问题，工作人员会尽快受理。以上惩罚措施会严格执行，请各位用户管理好自己的预约帐户，以免产生不必要的麻烦！"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/all")
    public Result getAll() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "# 预约挂号须知总则\n\n为提高患者预约效率，改善患者预约体验，北京市卫生和计划生育委员会本着方便百姓就医的服务宗旨,建立北京市预约挂号统一平台（以下简称\"统一平台\"），为保证您预约挂号成功，请您认真阅读预约挂号服务须知：\n\n**预约电话：** 114（北京用户）、010-114（非北京用户），提供7\\*24小时服务。\n**预约网址：**  [www.114yygh.com](http://www.114yygh.com/)\n\n### 一、预约范围\n\n1. 统一平台不提供当日预约服务，用户可预约接入统一平台医院的大部分科室次日起至三个月内的就诊号源，统一平台按照医院、科室、职称、专业不点名预约。具体预约挂号周期和号源信息，以114服务电话查询和网站公示为准。\n2. 每天可预约次日号源的服务时间截止到15:00前。（注：个别医院停止预约时间有所不同，具体以各家医院的个性化预约须知为准）\n\n### 二、实名制注册预约\n\n1. 统一平台电话预约和网上预约挂号采取实名制注册，用户首次预约必须注册就诊人的真实有效基本信息，包括就诊人员的真实姓名、有效证件号（包括居民身份证、港澳居民居住证、台湾居民居住证、港澳居民来往内地通行证、台湾居民来往大陆通行证、外国人永久居留身份证、护照）、性别、电话、手机号码等基本信息。\n2. 用户到医院取号就诊时须出示与预约登记实名信息一致的本人有效证件，否则医院不予办理挂号业务及诊疗服务。\n3. 统一平台114电话注册和网站注册，1个手机号只能关联1个有效证件号（一个账号能够关联3个常用就诊人，包括自己在内都可以随时挂号）。\n4. 网站注册预约须要求用户准备好手机，\"手机短信验证\" 是网站注册预约流程的重要环节，没有通过验证，用户无法成功注册预约，故请务必填写真实有效的手机号码。\n5. 用户也可通过114（非北京用户010-114）电话服务热线进行人工注册、预约，拨打114电话注册时需登记真实有效的手机号码或者座机号码。（建议使用手机号码注册预约，以便接收短信通知和预约、查询使用；若用户想要使用统一平台预约功能则需要在平台上重新验证手机号并设计密码，完成大平台的验证登录流程，才能使用114坐席的注册账号在统一平台上预约）（ 详细了解\"无身份证号，或者已经申报户口但还未办理身份证的患儿如何注册预约？\" ）\n\n### 三、账户信息管理\n\n1. 请保证您的统一平台账户信息真实有效，如用错误信息，或虚假信息预约挂号，不仅在就诊当日影响您取号就诊，同时也会影响到您今后的预约行为。\n2. 注册用户如名下存在未就诊的预约号源时，无法申请修改注册信息，请先取消所有当前有效预约号源或等约成号源过期的次日后再申请修改。\n3. 用户如修改本人注册姓名、有效证件号、手机号，须在线提出申请，须提供用户本人手持注册时的有效证件的人证合一，以便工作人员核实信息，我们将在5个工作日内协助处理完成，并以电话或短信方式通知到您，请保持电话畅通。（注：如原注册手机号有效，用户可选择在线自助完成更换手机号的工作）\n4. 统一平台不支持同时修改姓名和证件号码的申请。（注：该申请等同于重新注册）\n5. 如遇\"无法登录\"等特殊情况，用户可在线申诉，或拨打010-114协助解决。\n6. 为保证注册用户信息安全，申请人工协助处理修改注册信息时，须提供用户本人手持注册时的有效证件的人证合一照片，请保证脸部和证件的清晰，敬请理解！\n\n### 四、预约确认与就诊\n\n1. 通过网站或114电话预约成功后，系统会自动下发预约成功短信及唯一的8位数字识别码到用户注册手机上；如果114电话注册的是座机电话，工作人员每次会通过口述方式把预约信息报给您听，请您做好记录保存。\n2. 预约成功后，请您妥善保存\"预约成功短信和预约识别码\"，以便随时查询和取消预约信息之用，同时也是您用于取号就诊的凭证之一。\n3. 如果您未收到或丢失\"预约成功短信和预约识别码\"，可登录统一平台网站的\"个人中心>预约管理>当前预约\"中找到相应的订单查看详情，或者拨打114客服电话协助查询，工作人员与您核实信息后，系统会重新为您发送约成短信和识别码。\n4. 成功预约挂号后，系统将自动保存用户预约记录。就诊当天，您需要在医院规定的取号时间之内，前往医院指定的地点取号就诊，并缴纳医院规定的医事服务费，逾期预约自动作废。具体取号时间和取号地点请查阅系统下发的预约成功通知短信，或各家医院的个性化预约须知。\n5. 本着方便患者的原则，个别医院开通了自助取号或分诊台先取号后缴费服务，具体取号流程和要求以医院现场公示为准。如患者就诊后未缴费，将被列入欠费名单，您将无法通过统一平台或在各医院进行任何形式的预约挂号和窗口挂号。\n6. 取号时需要提供以下重要凭证：\n   * 就诊当日取号时，就诊人需凭就诊人本人注册的有效证件原件、预约成功通知短信和预约识别码至医院指定的挂号窗口取号（个别医院要求先办理就诊卡并关联社保卡后再取号，具体详见各家医院的个性化预约须知）；取号时医务人员将核对就诊者的身份信息和预约记录，如信息验证不符，则医院不能提供相应的诊疗服务。\n   * \"首都医科大学附属北京儿童医院（北京儿童医院）\"和\"首都儿科研究所附属儿童医院（儿研所）\"预约取号比较特殊。\n     * 预约\"北京儿童医院\"，就诊当日须出示监护人注册时的有效证件取号就诊。同时，就诊当日必须带患儿就诊，否则此号源作废。\n     * 预约\"儿研所\"，就诊当日须出示患儿的有效证件（包括居民身份证、北京社保卡、港澳居民居住证、台湾居民居住证、港澳居民来往内地通行证、台湾居民来往大陆通行证、外国人永久居留身份证、护照），及医保卡或京医通卡取号就诊,无京医通卡患儿可到门诊大厅建卡中心办理京医通卡后持卡取号。\n\n### 五、查询、取消预约\n\n1. 用户在医院规定的取消时限内可以取消预约号，超过退号截止时间系统将限制用户退号，一般截止时间为就诊日期的前一天下午15:00点，就诊当天不能取消当日预约号。（注：个别医院的预约取消截止时间有所不同，具体规则请关注各家医院的个性化预约须知）\n2. 在医院规定的取消预约时限内，用户可登录统一平台网站的\"个人中心\"直接取消，或拨打114客服电话进行查询/退号操作，退号时需凭\"预约识别码\"进行取消。\n3. 如果您在就诊当天不能前往医院取号就诊，请提前退号，否则会因造成号源的浪费，影响其他患者的就诊而被记录爽约档案，由此会影响到您今后的预约行为，请您谅解。\n\n### 六、预约限制\n\n为了防止号贩子倒号行为，满足绝大多数百姓的最大利益和公平性，统一平台采取了预约挂号限制。按照北京市卫生和计划生育委员会统一平台提供的预约挂号服务公约，做出如下限制：\n1.  同一自然日，同一医院，同一科室，同一就诊单元，同一就诊人，可以预约最多1个号源（就诊单元分为上午、下午、晚上、全天）；\n2. 同一自然日，同一就诊单元，同一就诊人，不同医院，可以预约最多3个号源（就诊单元分为上午、下午、晚上、全天）；\n3. 同一自然日，同一就诊人，可以预约最多4个号源；\n4. 同一自然周（周一到周日），同一就诊人，可以预约最多14个号源；\n5. 同一自然月，同一就诊人，可以预约最多20个号源；\n6. 同一自然季度内，同一就诊人，可以预约最多30个号源。\n\n同一自然日，同一就诊人，最多取消2个号源\n\n因患者个人原因主动退号，所退号源仍占用挂号名额；因医生停诊、系统问题等导致等退号，不占用可挂号的配额。\n\n### 七、爽约的判定及处罚\n\n1. 爽约是指用户未按医院规定的取号时间提前到医院指定的挂号窗口取号，且超过预约就诊时间未能按时就诊即视为爽约，该预约号源自动作废。如仍需就诊可重新预约或到医院挂号窗口挂号。\n2. 一年内（自然年）无故爽约累计达到3次的爽约用户将自动进入系统爽约名单，此后3个月内将取消其预约挂号资格。\n### 八、黑名单用户的判定及处罚\n\n黑名单用户判定规则\n\n| 编号 | 定义范围 | 解释                                                         |\n| ---- | -------- | ------------------------------------------------------------ |\n| 1    | 注册环节 | 身份证号不符合规范；                                         |\n| 2    | 预约环节 | 同一手机号在现有预约规则下3个月内预约大于15个号源（不含15个）； |\n| 3    | 取消环节 | 频繁取消，1个月内频繁取消大于15个号源（不含15个）；          |\n\n黑名单用户处罚规则\n\n | 操作 | 权限 |\n| ---- | -------- | \n| 登录 | 有权限 |\n| 使用个人中心  | 无权限 |\n| 预约  | 无权限 |\n| 注销预约  | 有权限 |\n| 注册信息修改  | 无权限 |\n\n### 九、特别声明\n\n1. 以上为统一平台服务总则，各家医院的特殊预约规则详见医院首页公示的\"预约须知\"。\n2. 统一平台不收取医事服务费，各家医院仅收取与医生职称相对应的医事服务费，如与您预约时不符，则以当日医院挂号窗口为准。\n3. 用户如需医保报销，请预约医保限定的定点报销医院。\n4. 请不要随意向他人泄露自己的实名身份信息，由于统一平台对预约者实行实名身份信息绑定，这样做会导致您的实名身份信息进入管制名单而无法进行预约。\n5. 本服务为用户提供便捷的预约方式，不承诺所有用户随时都能约到指定的号，有不便之处敬请谅解，有任何好的建议也可以直接向我们反馈。"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/flow")
    public Result getFlow() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "# 网站挂号流程\n\n使用统一平台网站预约挂号服务前请仔细阅读以下使用说明。\n\n### 一、注册登录\n\n登录网上预约挂号统一平台[www.114yygh.com](www.114yygh.com)，首次预约挂号须进行在线实名制注册，通过注册账号登录，进入预约挂号流程。（已注册用户直接登录开始预约挂号）\n\n### 二、选择医院/科室/疾病\n\n您可以通过多种搜索方式找到您所要预约的医院。\n![](http://img.114yygh.com/static/web/help_search.png)\n进入医院页面 选择需要预约的科室\n![](http://img.114yygh.com/static/web/help_hosp.png)\n### 三、选择日期/医生\n\n选择就诊日期，蓝色代表有可预约号源，点击预约；灰色代表号源已约满或停诊；白色代表无号源，无法点击。\n![](http://img.114yygh.com/static/web/help_source.png)\n### 四、填写预约信息并短信验证\n\n填写\"就诊卡号\"，\"医保卡号\"，选择\"报销类型\"；（选填）\n点击“获取”按钮，通过注册手机号接收短信验证码，并正确填写；（必填）\n点击”确认提交”在弹出本次预约成功信息时，即为成功预约。\n![](http://img.114yygh.com/static/web/help_add_patient.png)\n![](http://img.114yygh.com/static/web/help_card.png)\n![](http://img.114yygh.com/static/web/help_order_code.png)\n### 五、接收预约成功短信\n\n用户手机接收到预约成功短信及唯一的8位数字预约识别码。\n\n### 六、医院就诊\n\n就诊当日，请用户在规定的时间段内，前往医院凭就诊者本人预约登记时的有效证件和预约识别码就诊。\n\n# 电话挂号流程\n\n使用统一平台电话预约挂号服务前请仔细阅读以下使用说明。\n* 电话预约挂号服务（114）由中国联通北京市分公司提供，可为患者提供人工预约挂号、查询、取消、停诊通知及相关问题咨询等服务；\n* 预约电话：114（北京用户）、010-114（非北京用户），提供7*24小时服务；\n* 预约挂号高峰时段，系统可能会提示忙线状态，请耐心等待或通过网站预约；\n* 预约前，请提前准备好患者有效证件（居民身份证、北京社保卡、港澳居民居住证、台湾居民居住证、港澳居民来往内地通行证、台湾居民来往大陆通行证、外国人永久居留身份证、护照）；\n\n### 一、电话注册\n用户可通过拨打114（非北京用户010-114）电话服务热线人工注册预约挂号统一平台。\n### 二、电话预约\n用户通过114电话预约，预约成功后系统会自动下发预约成功短信及唯一的8位数字识别码到用户手机上。如果您未收到\"预约成功短信和识别码\"，请与114及时联系，人工核实信息后，系统会重新给您电话注册的手机发送短信。（如不方便接收短信，则由工作人员口头告知）\n\n### 三、电话取消预约\n\n如您因故不能按时就诊，请务必在就诊日前一天15:00前（个别医院14:00前）拨打114电话凭预约识别码取消预约（注：个别医院取消预约截止时间有所不同，具体规则请关注各家医院的个性化预约须知）。\n如未能按时就诊，也未能及时取消预约的用户，将会被记录爽约一次，无故爽约累计达到3次将进入系统爽约名单，此后3个月内将无法享受预约挂号服务。\n\n### 四、电话查询预约信息\n\n您可拨打114客服热线凭预约识别码或注册手机号人工查询预约信息。"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/query")
    public Result getQuery() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "### 如何查询/取消预约\n\n在医院规定的取消预约时限内，用户可登录统一平台网站的\"个人中心\"，或拨打114客服电话进行查询/退号操作，退号时需凭\"预约识别码\"进行取消。如果您在就诊当天不能前往医院取号就诊，请提前退号，否则会因造成号源的浪费，影响其他患者的就诊而被记录爽约档案，由此会影响到您今后的预约行为，请您谅解。\n**方法一：**\n进入用户的\"个人中心\"，进入\"个人中心>预约信息\"中的预约挂号列表，查看或取消您的预约订单，点击取消即可。\n![](http://img.114yygh.com/static/web/help_order_list.png)\n![](http://img.114yygh.com/static/web/help_order_detail_cancel.png)\n**方法二：**\n拨打114客服电话人工协助查询/取消预约信息。电话退号流程如下：\n![](http://img.114yygh.com/static/web/help_cancel.png)\n\n"
        );
        return Result.success(jsonObject);
    }

    @GetMapping("/appeal")
    public Result getAppeal() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "### 账号申诉\n账号申诉能够帮您处理以下五种问题。\n\n1. 实名认证未通过\n    * 注册账户/添加就诊人时，提示“实名认证失败”\n    \n2. 实名信息被绑定\n    * 注册账户实名认证时，提示实名信息被其他账户占用\n\n3. 就诊人被绑定\n    * 就诊人被其他账户绑定时，就诊人本人可进行申诉找回\n\n\n4. 注销注册手机号\n    * 注销“申诉人实名信息”所绑定的账户；注销后账户在平台所产生的全部历史记录均被删除\n\n\n5. 其他\n    * 以上选项均不是要选择的申诉类型\n    \n    \n请扫描以下二维码关注“北京114预约挂号”微信公众号，选择【账号申诉】进入申诉页面。\n\n![](http://img.114yygh.com/image/image-001/23636222840745840.png)"
        );
        return Result.success(jsonObject);
    }


}