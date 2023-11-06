const router = require(`express`).Router()

const carPartsSchema = require(`../models/carparts`)

const multer  = require('multer')
var upload = multer({dest: `${process.env.UPLOADED_FILES_FOLDER}`})


router.get(`/carParts`, (req, res) => {
    carPartsSchema.find((error, data) => {
        res.json(data)
    })
})

router.get(`/cars/photo/:filename`, (req, res) => 
{   
    fs.readFile(`${process.env.UPLOADED_FILES_FOLDER}/${req.params.filename}`, 'base64', (err, fileData) => 
        {        
            if(fileData)
            {  
                res.json({image:fileData})                           
            }   
        else
        {
            res.json({image:null})
        }
    })             
})

router.get(`/carParts/:id`, (req, res) =>{
    carPartsSchema.findById(req.params.id, (error, data) =>{
        res.json(data)
    })
})


router.get(`/carParts/:id`, (req, res) =>{
    carPartsSchema.findById(req.params.id, (error, data) =>{
        res.json(data)
    })
})

router.get(`/cars/photo/:filename`, (req, res) => 
{   
    fs.readFile(`${process.env.UPLOADED_FILES_FOLDER}/${req.params.filename}`, 'base64', (err, fileData) => 
        {        
            if(fileData)
            {  
                res.json({image:fileData})                           
            }   
        else
        {
            res.json({image:null})
        }
    })             
})


router.put(`/carParts/:id`, (req, res) => {
    carPartsSchema.findByIdAndUpdate(req.params.id, { $set: req.body }, (error, data) => {
        res.json(data)
    })
})


router.post(`/carParts`, (req, res) => {
    carPartsSchema.create(req.body, (error, data) => {
        console.log(error)
        res.json(data)
    })

})

router.delete(`/carParts/:id`, (req, res) => {
     carPartsSchema.findByIdAndRemove(req.params.id, (error, data) => {
        res.json(data)
    })
})

module.exports = router