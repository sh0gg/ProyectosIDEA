from odoo import models, fields, api
class aula(models.Model):

    _name="colegio.aula"
    _description="Tabla para la aplicacion"

    nombre = fields.Char("Nombre aula",required=True)
    piso = fields.Text("Descripcion",required=True)
    capacidad = fields.Integer("Numero de sitios",required=True)
    disponibilidad = fields.Boolean("Si / No",default=True)