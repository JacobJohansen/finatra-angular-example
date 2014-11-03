package com.acme.ShoppingCart.traits

import com.acme.ShoppingCart.exception.BadRequest
import com.acme.ShoppingCart.models.ProductsModel
import com.twitter.finatra.Request

trait Products extends ParamsValidation {
  def getProductId(request: Request) = {
    val productId = getParam(request.routeParams, "productId")

    ProductsModel getProductById productId.toInt match {
      case x +: xs => x.id.get
      case _ => throw new BadRequest("Product with provided id '" ++ productId ++ "' is not exist!")
    }
  }

  def getProductQuantity(request: Request) = {
    val quantity = getParam(request.routeParams, "quantity").toInt

    quantity match {
      case amount if amount < 0 => throw new BadRequest("Quantity should be positive value!")
      case _ => quantity
    }
  }
}
