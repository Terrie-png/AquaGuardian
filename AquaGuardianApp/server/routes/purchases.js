const router = require(`express`).Router()

const purchaseModelSchema = require(`../models/purchases`)

router.post(`/purchase`, (req, res) => {
    purchaseModelSchema.create(req.body, (error, data)  =>{
        if(error){
            console.log(error)
        }
        res.json(data)
    })
})

router.get(`/purchases/:id`, (req, res) =>{
    purchaseModelSchema.find({_id: req.params._id}, (error, data) =>{
        res.json(data)
    }) 
})

module.exports = router