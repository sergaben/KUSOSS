package models.Intefaces

import models.TypeOfStudy

trait ITypeOfStudyRepository {
    def add(typeOfStudy:TypeOfStudy):Unit
    def update(typeOfStudy: TypeOfStudy):Unit
    def remove(typeOfStudy:TypeOfStudy):Unit
    def getAll():Seq[TypeOfStudy]
    def getById(id:Int):TypeOfStudy
}
