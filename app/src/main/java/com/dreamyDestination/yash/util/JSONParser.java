package com.dreamyDestination.yash.util;

import com.dreamyDestination.yash.entity.DietPlanModel;
import com.dreamyDestination.yash.entity.ELUserInfo;
import com.dreamyDestination.yash.entity.FeedbackModel;
import com.dreamyDestination.yash.entity.PlacesDetailListModel;
import com.dreamyDestination.yash.entity.RegisteredUserModel;
import com.dreamyDestination.yash.entity.TravelTipURL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Rupesh on 18-Jul-16.
 */
public class JSONParser {

    static String TAG = JSONParser.class.getSimpleName();

    public static ELUserInfo parseUserData(String jsonString) {

        ELUserInfo UserInfo = null;

        if (jsonString != null) {

            try {

                JSONObject rootjsonobj = new JSONObject(jsonString);

                if (rootjsonobj != null) {

                    JSONObject jsonobj = rootjsonobj.getJSONObject("user");

                    UserInfo = new ELUserInfo();

                    if (jsonobj.has("faculty_username")) {
                        if (!jsonobj.isNull("faculty_username")) {
                            UserInfo.setFullName(jsonobj.getString("faculty_username"));
                        }
                    }
                    if (jsonobj.has("faculty_password")) {
                        if (!jsonobj.isNull("faculty_password")) {
                            UserInfo.setEmail(jsonobj.getString("faculty_password"));
                        }
                    }

                    if (jsonobj.has("fullname")) {
                        if (!jsonobj.isNull("fullname")) {
                            UserInfo.setFullName(jsonobj.getString("fullname"));
                        }
                    }

//                    if (jsonobj.has("fullname")) {
//                        if (!jsonobj.isNull("fullname")) {
//                            UserInfo.setFullName(jsonobj.getString("fullname"));
//                        }
//                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return UserInfo;
    }

    public static ArrayList<TravelTipURL> parseTravelTrip(String jsonString) {

        ArrayList<TravelTipURL> studentDetailListInfo = new ArrayList<>();

        if (jsonString != null) {

            try {

                JSONArray jsonarr = new JSONArray(jsonString);

                if (jsonarr != null)
                {

                    //JSONArray jsonarr = rootjsonobj.getJSONArray("Couriers");

                    if (jsonarr != null) {
                        if (jsonarr.length() > 0) {

                            for (int i = 0; i < jsonarr.length(); i++) {

                                JSONObject jsonobj = jsonarr.getJSONObject(i);

                                TravelTipURL appList = new TravelTipURL();

                                if (jsonobj.has("travel_description")) {
                                    if (!jsonobj.isNull("travel_description")) {
                                        appList.setTravel_description(jsonobj.getString("travel_description"));
                                    }
                                }
                                if (jsonobj.has("travel_description_type")) {
                                    if (!jsonobj.isNull("travel_description_type")) {
                                        appList.setTravel_description_type(jsonobj.getString("travel_description_type"));
                                    }
                                }

                                if (jsonobj.has("date_added")) {
                                    if (!jsonobj.isNull("date_added")) {
                                        appList.setDate_added(jsonobj.getString("date_added"));
                                    }
                                }


                                studentDetailListInfo.add(appList);

                            }

                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return studentDetailListInfo;
    }


    public static ArrayList<FeedbackModel> parseFeedback(String jsonString) {

        ArrayList<FeedbackModel> studentDetailListInfo = new ArrayList<>();

        if (jsonString != null) {

            try {

                JSONArray jsonarr = new JSONArray(jsonString);

                if (jsonarr != null)
                {

                    //JSONArray jsonarr = rootjsonobj.getJSONArray("Couriers");

                    if (jsonarr != null) {
                        if (jsonarr.length() > 0) {

                            for (int i = 0; i < jsonarr.length(); i++) {

                                JSONObject jsonobj = jsonarr.getJSONObject(i);

                                FeedbackModel appList = new FeedbackModel();

                                if (jsonobj.has("feedback_rating")) {
                                    if (!jsonobj.isNull("feedback_rating")) {
                                        appList.setFeedback_rating(jsonobj.getString("feedback_rating"));
                                    }
                                }
                                if (jsonobj.has("comment")) {
                                    if (!jsonobj.isNull("comment")) {
                                        appList.setComment(jsonobj.getString("comment"));
                                    }
                                }

                                if (jsonobj.has("posted_by")) {
                                    if (!jsonobj.isNull("posted_by")) {
                                        appList.setPosted_by(jsonobj.getString("posted_by"));
                                    }
                                }

                                if (jsonobj.has("posted_on")) {
                                    if (!jsonobj.isNull("posted_on")) {
                                        appList.setPosted_on(jsonobj.getString("posted_on"));
                                    }
                                }


                                studentDetailListInfo.add(appList);

                            }

                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return studentDetailListInfo;
    }


    public static ArrayList<PlacesDetailListModel> parsePlacesDetailList(String jsonString) {

        ArrayList<PlacesDetailListModel> studentDetailListInfo = new ArrayList<>();

        if (jsonString != null) {

            try {

                JSONArray jsonarr = new JSONArray(jsonString);

                if (jsonarr != null)
                {

                    //JSONArray jsonarr = rootjsonobj.getJSONArray("Couriers");

                    if (jsonarr != null) {
                        if (jsonarr.length() > 0) {

                            for (int i = 0; i < jsonarr.length(); i++) {

                                JSONObject jsonobj = jsonarr.getJSONObject(i);

                                PlacesDetailListModel appList = new PlacesDetailListModel();

                                if (jsonobj.has("image_path")) {
                                    if (!jsonobj.isNull("image_path")) {
                                        appList.setImage_path(jsonobj.getString("image_path"));
                                    }
                                }
                                if (jsonobj.has("place_name")) {
                                    if (!jsonobj.isNull("place_name")) {
                                        appList.setPlace_name(jsonobj.getString("place_name"));
                                    }
                                }

                                if (jsonobj.has("place_type")) {
                                    if (!jsonobj.isNull("place_type")) {
                                        appList.setPlace_type(jsonobj.getString("place_type"));
                                    }
                                }

                                if (jsonobj.has("place_address")) {
                                    if (!jsonobj.isNull("place_address")) {
                                        appList.setPlace_address(jsonobj.getString("place_address"));
                                    }
                                }


                                studentDetailListInfo.add(appList);

                            }

                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return studentDetailListInfo;
    }



    public static ArrayList<RegisteredUserModel> parseTreeOwnerData(String jsonString) {

        ArrayList<RegisteredUserModel> studentDetailListInfo = new ArrayList<>();

        if (jsonString != null) {

            try {

                JSONArray jsonarr = new JSONArray(jsonString);

                if (jsonarr != null)
                {

                    //JSONArray jsonarr = rootjsonobj.getJSONArray("Couriers");

                    if (jsonarr != null) {
                        if (jsonarr.length() > 0) {

                            for (int i = 0; i < jsonarr.length(); i++) {

                                JSONObject jsonobj = jsonarr.getJSONObject(i);

                                RegisteredUserModel appList = new RegisteredUserModel();

                                if (jsonobj.has("user_full_name")) {
                                    if (!jsonobj.isNull("user_full_name")) {
                                        appList.setUser_full_name(jsonobj.getString("user_full_name"));
                                    }
                                }
                                if (jsonobj.has("user_email")) {
                                    if (!jsonobj.isNull("user_email")) {
                                        appList.setUser_email(jsonobj.getString("user_email"));
                                    }
                                }

                                if (jsonobj.has("user_unique_id")) {
                                    if (!jsonobj.isNull("user_unique_id")) {
                                        appList.setUser_id(jsonobj.getString("user_unique_id"));
                                    }
                                }

                                studentDetailListInfo.add(appList);

                            }

                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return studentDetailListInfo;
    }


    public static ELUserInfo parseStudentData(String jsonString) {

        ELUserInfo studentInfo = null;

        if (jsonString != null) {

            try {

                JSONObject rootjsonobj = new JSONObject(jsonString);

                if (rootjsonobj != null) {

                    JSONObject jsonobj = rootjsonobj.getJSONObject("user");

                    studentInfo = new ELUserInfo();

                    if (jsonobj.has("user_full_name")) {
                        if (!jsonobj.isNull("user_full_name")) {
                            studentInfo.setFullName(jsonobj.getString("user_full_name"));
                        }
                    }
                    if (jsonobj.has("user_email")) {
                        if (!jsonobj.isNull("user_email")) {
                            studentInfo.setEmail(jsonobj.getString("user_email"));
                        }
                    }

                    if (jsonobj.has("user_password")) {
                        if (!jsonobj.isNull("user_password")) {
                            studentInfo.setUser_password(jsonobj.getString("user_password"));
                        }
                    }

                    if (jsonobj.has("user_joined_date")) {
                        if (!jsonobj.isNull("user_joined_date")) {
                            studentInfo.setJoined_date(jsonobj.getString("user_joined_date"));
                        }
                    }

                    if (jsonobj.has("user_unique_id")) {
                        if (!jsonobj.isNull("user_unique_id")) {
                            studentInfo.setUser_unique_id(jsonobj.getString("user_unique_id"));
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return studentInfo;
    }

//
//
//    public static ArrayList<DoctorAppointment> parseAppointmentData(String jsonString) {
//
//        ArrayList<DoctorAppointment> appointmentList = new ArrayList<>();
//
//        if (jsonString != null) {
//
//            try {
//
//                //JSONObject rootjsonobj = new JSONObject(jsonString);
//                JSONArray jsonarr = new JSONArray(jsonString);
//
//                if (jsonarr != null) {
//
//                    //JSONArray jsonarr = rootjsonobj.getJSONArray("Couriers");
//
//                    if (jsonarr != null) {
//                        if (jsonarr.length() > 0) {
//
//                            for (int i = 0; i < jsonarr.length(); i++) {
//
//                                JSONObject jsonobj = jsonarr.getJSONObject(i);
//
//                                DoctorAppointment appList = new DoctorAppointment();
//
//                                if (jsonobj.has("patient_name")) {
//                                    if (!jsonobj.isNull("patient_name")) {
//                                        appList.setPatientName(jsonobj.getString("patient_name").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("address")) {
//                                    if (!jsonobj.isNull("address")) {
//                                        appList.setAddress(jsonobj.getString("address").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("phonenumber")) {
//                                    if (!jsonobj.isNull("phonenumber")) {
//                                        appList.setPhoneNumber(jsonobj.getString("phonenumber").trim());
//                                    }
//                                }
//                                if (jsonobj.has("appointment_date")) {
//                                    if (!jsonobj.isNull("appointment_date")) {
//                                        appList.setAppointmentDate(jsonobj.getString("appointment_date").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("doctor_name")) {
//                                    if (!jsonobj.isNull("doctor_name")) {
//                                        appList.setDoctorName(jsonobj.getString("doctor_name").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("appointment_time")) {
//                                    if (!jsonobj.isNull("appointment_time")) {
//                                        appList.setAppointmentTime(jsonobj.getString("appointment_time").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("email")) {
//                                    if (!jsonobj.isNull("email")) {
//                                        appList.setEmail(jsonobj.getString("email").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("doctor_lat")) {
//                                    if (!jsonobj.isNull("doctor_lat")) {
//                                        appList.setDoctorLat(jsonobj.getString("doctor_lat").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("dotor_long")) {
//                                    if (!jsonobj.isNull("dotor_long")) {
//                                        appList.setDoctorLong(jsonobj.getString("dotor_long").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("patient_lat")) {
//                                    if (!jsonobj.isNull("patient_lat")) {
//                                        appList.setPatientLat(jsonobj.getString("patient_lat").trim());
//                                    }
//                                }
//
//                                if (jsonobj.has("patient_long")) {
//                                    if (!jsonobj.isNull("patient_long")) {
//                                        appList.setPatientLong(jsonobj.getString("patient_long").trim());
//                                    }
//                                }
//                                if (jsonobj.has("doctor_username")) {
//                                    if (!jsonobj.isNull("doctor_username")) {
//                                        appList.setDoctorUsername(jsonobj.getString("doctor_username").trim());
//                                    }
//                                }
//                                if (jsonobj.has("appointment_status")) {
//                                    if (!jsonobj.isNull("appointment_status")) {
//                                        appList.setAppointmentStatus(jsonobj.getInt("appointment_status"));
//                                    }
//                                }
//                                appointmentList.add(appList);
//
//                            }
//
//                        }
//                    }
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return appointmentList;
//    }
//
//
//    public static ELDoctorInfo parseDoctorData(String jsonString) {
//
//        ELDoctorInfo doctorInfo = null;
//
//        if (jsonString != null) {
//
//            try {
//
//                JSONObject rootjsonobj = new JSONObject(jsonString);
//
//                if (rootjsonobj != null) {
//
//                    JSONObject jsonobj = rootjsonobj.getJSONObject("user");
//
//                    doctorInfo = new ELDoctorInfo();
//
//                    if (jsonobj.has("doctor_name")) {
//                        if (!jsonobj.isNull("doctor_name")) {
//                            doctorInfo.setDoctorFullname(jsonobj.getString("doctor_name"));
//                        }
//                    }
//                    if (jsonobj.has("doctor_degree")) {
//                        if (!jsonobj.isNull("doctor_degree")) {
//                            doctorInfo.setDoctorDegree(jsonobj.getString("doctor_degree"));
//                        }
//                    }
//
//                    if (jsonobj.has("doctor_address")) {
//                        if (!jsonobj.isNull("doctor_address")) {
//                            doctorInfo.setDoctorAddress(jsonobj.getString("doctor_address"));
//                        }
//                    }
//
//                    if (jsonobj.has("doctor_lat")) {
//                        if (!jsonobj.isNull("doctor_lat")) {
//                            doctorInfo.setDoctorLat(jsonobj.getString("doctor_lat"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("dotor_long")) {
//                        if (!jsonobj.isNull("dotor_long")) {
//                            doctorInfo.setDoctorLong(jsonobj.getString("dotor_long"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("doctor_summary")) {
//                        if (!jsonobj.isNull("doctor_summary")) {
//                            doctorInfo.setDoctorSummary(jsonobj.getString("doctor_summary"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("doctor_timing")) {
//                        if (!jsonobj.isNull("doctor_timing")) {
//                            doctorInfo.setDoctorTimings(jsonobj.getString("doctor_timing"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("doctor_date")) {
//                        if (!jsonobj.isNull("doctor_date")) {
//                            doctorInfo.setDoctorDate(jsonobj.getString("doctor_date"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("doctor_fees")) {
//                        if (!jsonobj.isNull("doctor_fees")) {
//                            doctorInfo.setDoctorFees(jsonobj.getString("doctor_fees"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("doctor_experience")) {
//                        if (!jsonobj.isNull("doctor_experience")) {
//                            doctorInfo.setDoctorExperinece(jsonobj.getString("doctor_experience"));
//                        }
//                    }
//
//
//                    if (jsonobj.has("doctor_specification")) {
//                        if (!jsonobj.isNull("doctor_specification")) {
//                            doctorInfo.setDoctorSpecification(jsonobj.getString("doctor_specification"));
//                        }
//                    }
//
//                    if (jsonobj.has("doctor_username")) {
//                        if (!jsonobj.isNull("doctor_username")) {
//                            doctorInfo.setDoctorUsername(jsonobj.getString("doctor_username"));
//                        }
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return doctorInfo;
//    }

    /**
     public static ArrayList<ELRatingDetails> parseRatingDetails(String jsonString, Context context) {

     ArrayList<ELRatingDetails> Ratings = new ArrayList<>();

     if (jsonString != null) {

     try {

     JSONObject rootjsonobj = new JSONObject(jsonString);

     if (rootjsonobj != null) {

     JSONArray jsonarr = rootjsonobj.getJSONArray("RatingMasterDetails");

     if (jsonarr != null) {
     if (jsonarr.length() > 0) {

     for (int i = 0; i < jsonarr.length(); i++) {

     JSONObject jsonobj = jsonarr.getJSONObject(i);

     ELRatingDetails rating = new ELRatingDetails();

     if (jsonobj.has("ratMasterID")) {
     if (!jsonobj.isNull("ratMasterID")) {
     rating.setRatingID(jsonobj.getInt("ratMasterID"));
     }
     }

     if (jsonobj.has("ratMasterLangCode")) {
     if (!jsonobj.isNull("ratMasterLangCode")) {
     rating.setLangCode(jsonobj.getString("ratMasterLangCode").trim());
     }
     }

     if (jsonobj.has("ratMasterRatings")) {
     if (!jsonobj.isNull("ratMasterRatings")) {
     rating.setRatings(Float.parseFloat(jsonobj.getString("ratMasterRatings").trim()));
     }
     }

     if (jsonobj.has("ratMasterDesc")) {
     if (!jsonobj.isNull("ratMasterDesc")) {
     rating.setDescription(jsonobj.getString("ratMasterDesc").trim());
     }
     }


     Ratings.add(rating);

     }

     }
     }

     }

     } catch (Exception e) {
     e.printStackTrace();
     }
     }

     return Ratings;
     }

     public static ArrayList<ELLanguage> parseLanguageDetails(String jsonString, Context context) {

     ArrayList<ELLanguage> Languages = new ArrayList<>();

     if (jsonString != null) {

     try {

     JSONObject rootjsonobj = new JSONObject(jsonString);

     if (rootjsonobj != null) {

     JSONArray jsonarr = rootjsonobj.getJSONArray("LanguageMasterDetails");

     if (jsonarr != null) {
     if (jsonarr.length() > 0) {

     for (int i = 0; i < jsonarr.length(); i++) {

     JSONObject jsonobj = jsonarr.getJSONObject(i);

     ELLanguage language = new ELLanguage();

     if (jsonobj.has("langCode")) {
     if (!jsonobj.isNull("langCode")) {
     language.setLangCode(jsonobj.getString("langCode").trim());
     }
     }

     if (jsonobj.has("langDesc")) {
     if (!jsonobj.isNull("langDesc")) {
     language.setLangDesc(jsonobj.getString("langDesc").trim());
     }
     }

     Languages.add(language);

     }

     }
     }

     }

     } catch (Exception e) {
     e.printStackTrace();
     }
     }

     return Languages;
     }

     public static ArrayList<ELLiteral> parseLiteralDetails(String jsonString, Context context) {

     ArrayList<ELLiteral> Literals = new ArrayList<>();

     if (jsonString != null) {

     try {

     JSONObject rootjsonobj = new JSONObject(jsonString);

     if (rootjsonobj != null) {

     JSONArray jsonarr = rootjsonobj.getJSONArray("LiteralsMasterDetails");

     if (jsonarr != null) {
     if (jsonarr.length() > 0) {

     for (int i = 0; i < jsonarr.length(); i++) {

     JSONObject jsonobj = jsonarr.getJSONObject(i);

     ELLiteral literal = new ELLiteral();

     if (jsonobj.has("langCode")) {
     if (!jsonobj.isNull("langCode")) {
     literal.setLangCode(jsonobj.getString("langCode").trim());
     }
     }

     if (jsonobj.has("literalDesc")) {
     if (!jsonobj.isNull("literalDesc")) {
     literal.setLiteralDesc(jsonobj.getString("literalDesc").trim());
     }
     }

     if (jsonobj.has("literalCode")) {
     if (!jsonobj.isNull("literalCode")) {
     literal.setLiteralCode(jsonobj.getString("literalCode").trim());
     }
     }

     Literals.add(literal);

     }

     }
     }

     }

     } catch (Exception e) {
     e.printStackTrace();
     }
     }

     return Literals;
     }

     */

}
