package models

import java.sql.Timestamp


case class Ku_student(id : Int,
                      nickname : String,
                      password : String,
                      email : String,
                      subject_id : Int,
                      type_of_study_id : Int,
                      from_kingston : Boolean,
                      expiration_time_of_user : Option[Timestamp])

