const router = require(`express`).Router()

const salesModel = require(`../models/sales`)
const carPartsModel = require(`../models/carParts`)


const createNewSaleDocument = (req, res, next) => 
{           
    // Use the PayPal details to create a new sale document                
    let saleDetails = new Object()
           
    saleDetails.paypalPaymentID = req.params.paymentID
    saleDetails.partID = req.params.partID
    saleDetails.price = req.params.price
    saleDetails.customerName = req.params.customerName
    saleDetails.customerEmail = req.params.customerEmail
        
        
    carPartsModel.findByIdAndUpdate({_id:req.params.partID}, {sold: true}, (err, data) => 
    {
        if(err)
        {
            return next(err)
        }  
    }) 
    
    salesModel.create(saleDetails, (err, data) => 
    {
        if(err)
        {
            return next(err)
        }                        
    })   
    
    return res.json({success:true})
}


// Save a record of each Paypal payment
router.post('/sales/:paymentID/:partID/:price/:customerName/:customerEmail', createNewSaleDocument)


module.exports = router