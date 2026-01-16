from odoo import api, fields, models


class Coche(models.Model):
    _name = "coches.coche"
    _description = "Coche"
    _rec_name = "name"

    name = fields.Char(string="Nombre", required=True)
    matricula = fields.Char(string="Matrícula", required=True, index=True)
    marca = fields.Char(string="Marca")
    modelo = fields.Char(string="Modelo")
    anio = fields.Integer(string="Año")
    color = fields.Char(string="Color")
    precio = fields.Monetary(string="Precio")
    currency_id = fields.Many2one(
        "res.currency",
        string="Moneda",
        default=lambda self: self.env.company.currency_id.id,
        required=True,
    )

    estado = fields.Selection(
        [
            ("borrador", "Borrador"),
            ("disponible", "Disponible"),
            ("vendido", "Vendido"),
        ],
        string="Estado",
        default="borrador",
        required=True,
        tracking=True,
    )

    active = fields.Boolean(default=True)
    company_id = fields.Many2one(
        "res.company",
        default=lambda self: self.env.company.id,
        required=True,
    )

    _sql_constraints = [
        ("matricula_unique", "unique(matricula, company_id)", "La matrícula ya existe en esta compañía."),
    ]

    @api.model_create_multi
    def create(self, vals_list):
        seq = self.env["ir.sequence"]
        for vals in vals_list:
            if vals.get("name") in (False, "", "Nuevo"):
                vals["name"] = seq.next_by_code("coches.coche") or "Nuevo"
        return super().create(vals_list)
