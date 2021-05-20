# QrScanner-AndroidAppProject
The idea behind my application is to replace the traditional paper patient card information with a digital one. 
Each patient that is accepted to the hospital receives a hand band with the QR code. All of the relevant information is attached to the patient's profile. Later, when a nurse or doctor needs to check some of the patient's data, all they have to do is to scan the QR code using my app. The application will display: personal patient information, prescripted drugs, and when to serve them, temperature, blood pressure, diagnosis,  symptoms, how long the patient should stay in the hospital, and other relevant data.

Moreover, the application can be also implemented for other usages such as: 

- Fairs where companies can scan the potential clients and add them to their list with all the necessary information, so they can message them with and offer after the fairs.

- Festivals, so the organizers can check what type of ticket does the client has and where can he/she enter, and what types of amusements are included in the price of his/her ticket.




MoSCoW requirements:

Must Have:
- Log in functionality
- QR code scanner functionality
- Connection to the database

Should Have:
- Intuitive UI
- Possibility to modify/add new information to the patient’s data
- ToDo/Notes section connected with the specific patient, so that doctor can create reminder to do some specific action with that patient <-  IMPLEMENTED DESCRIPTION IN MEASUREMENTS INSTEAD OF THIS ONE
- Log out functionality

Could Have:
- Dark mode <- NOT IMPLEMENTED

Will not Have:
- QR code generator
- Registration functionality <- NOT IMPLEMENTED

Libraries I used:
- Library for rounded pictures: implementation 'de.hdodenhof:circleimageview:2.1.0' 
- Library that automatically puts "." or "/" in the temperature and blood pressure: implementation 'com.github.santalu:maskara:1.0.0'
- Library for QrCodes scanning: implementation 'com.journeyapps:zxing-android-embedded:3.4.0', https://github.com/journeyapps/zxing-android-embedded
- Library used for swiping on the patients left and right to delete them or add to "favourite" (not implemented in the end, but for future implementaion):  implementation 'it.xabaras.android:recyclerview-swipedecorator:1.2.3' ,https://github.com/xabaras/RecyclerViewSwipeDecorator
