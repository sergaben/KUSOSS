package models.Intefaces

import models.Subject

trait ISubjectRepository {
    def add(subject:Subject):Unit
    def update(subject: Subject):Unit
    def delete(subject: Subject):Unit
    def getAll():Seq[Subject]
    def getById(id:Int):Subject
}
