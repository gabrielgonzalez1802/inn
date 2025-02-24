# Lista de servicios pendientes y según su prioridad.

1.  servicio que le mande un clientID y me retorne todas la direcciones de ese cliente

2.  como manejar cuando un tipo de cliente sea un agente de retención.
    ej:

        si la compañia de gabriel, se registra como GABO RESTAURANT, al momento de traerme la información del usuario debería traerme si es un agente de retención.

    una orden puede tener muchos impuestos.

    crear una tabla `order_taxes` para asociar todos los impuestos relacionados a una orden.

    atributos: `order_tax_id(PK)` , `type_taxes_id(FK)`, `payment_order_details_id(FK)`

3.  micro de tipos de pago (enriquecido).

    para que el usuario pueda seleccionar el tipo de pago se creó
    `payments_type` con estos atributos.
    payment_type_id , payment_type_name.

4.  enriquecer el micro de GetOrderBy id para traer toda la información relacionada a la orden.

    - Impuestos (enriquecido )
    - tipo de pago utilizado (enriquecido ).
    - moneda utilizada (enriquecido ).
    - productos de la orden con toda su información (enriquecido ).
    - Cliente de la orden (enriquecido ).
    - Estatus de la orden (enriquecido )
    - Dirección de entrega de la orden. confirmar la relación, para poder casar UNA orden con UNA direción de un cliente. (recordar que un cliente, puede tener varias direcciones.)

5.  crear endpoint dentro del micro servicio de ORDER para devolver todas las ordenes dado un client ID.
