const mongoose = require('mongoose')

//TODO add regexes to ensure valid data

let itemDataSchema = new mongoose.Schema(
    {
        itemId:{type: String}, 
        itemPrice:{type: Number, match:/^\d+$/}, 
        quantity:{type:Number, match:/^\d+$/}
    }
)

let purchaseSchema = new mongoose.Schema(
    {
        userId:{type:String},
        purchaseDate:{type:Date},
        purchaseStatus:{type:String, enum:["paid", "dispatched", "arrived", "cancelled"]},
        items: [itemDataSchema]
    },
    {
        collection: `purchases`
    }
)

module.exports = mongoose.model(`purchases`, purchaseSchema)