const mongoose = require(`mongoose`)

let carPhotosSchema = new mongoose.Schema(
    {
       filename:{type:String,default:"https://img.vast.com/usnews/3707326471654947088/1/320x240"}
    })


let carPartsSchema = new mongoose.Schema(
   {
        name: {type: String},
        item_number: {type: String},
        material: {type: String},
        colour: {type: String},
        price: {type: String},
        photos:[carPhotosSchema],
        condition: {type: String},
        quantity: {type: Number} 
   },
   {
       collection: `carParts`
   })

module.exports = mongoose.model(`cars`, carPartsSchema)