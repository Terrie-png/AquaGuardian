const router = require(`express`).Router()

const carsModel = require(`../models/cars`)

const jwt = require('jsonwebtoken')
const fs = require('fs')
const JWT_PRIVATE_KEY = fs.readFileSync(process.env.JWT_PRIVATE_KEY_FILENAME, 'utf8')

// read all records
router.get(`/cars`, (req, res) => 
{   
    //user does not have to be logged in to see car details
    carsModel.find((error, data) => 
    {
        res.json(data)
    })
})


// Read one record
router.get(`/cars/:id`, (req, res) => 
{
    jwt.verify(req.headers.authorization, JWT_PRIVATE_KEY, {algorithm: "HS256"}, (err, decodedToken) => 
    {
        if (err) 
        { 
            res.json({errorMessage:`User is not logged in`})
        }
        else
        {
            carsModel.findById(req.params.id, (error, data) => 
            {
                res.json(data)
            })
        }
    })
})


// Add new record
router.post(`/cars`, (req, res) => 
{
    jwt.verify(req.headers.authorization, JWT_PRIVATE_KEY, {algorithm: "HS256"}, (err, decodedToken) => 
    {
        if (err) 
        { 
            res.json({errorMessage:`User is not logged in`})
        }
        else
        {
            if(decodedToken.accessLevel >= process.env.ACCESS_LEVEL_ADMIN)
            {                
                // Use the new car details to create a new car document
                carsModel.create(req.body, (error, data) => 
                {
                    res.json(data)
                })
            }
            else
            {
                res.json({errorMessage:`User is not an administrator, so they cannot add new records`})
            }
        }
    })
})


// Update one record
router.put(`/cars/:id`, (req, res) => 
{
    jwt.verify(req.headers.authorization, JWT_PRIVATE_KEY, {algorithm: "HS256"}, (err, decodedToken) => 
    {
        if (err) 
        { 
            res.json({errorMessage:`User is not logged in`})
        }
        else
        {
            carsModel.findByIdAndUpdate(req.params.id, {$set: req.body}, (error, data) => 
            {
                res.json(data)
            })        
        }
    })
})


// Delete one record
router.delete(`/cars/:id`, (req, res) => 
{
    
    jwt.verify(req.headers.authorization, JWT_PRIVATE_KEY, {algorithm: "HS256"}, (err, decodedToken) => 
    {
        if (err) 
        { 
            res.json({errorMessage:`User is not logged in`})
        }
        else
        {
            if(decodedToken.accessLevel >= process.env.ACCESS_LEVEL_ADMIN)
            {
                carsModel.findByIdAndRemove(req.params.id, (error, data) => 
                {
                    res.json(data)
                })
            }
            else
            {
                res.json({errorMessage:`User is not an administrator, so they cannot delete records`})
            }        
        }
    })
})


// read all records
router.get(`/cars`, getAllCarDocuments)

// get one car photo
router.get(`/cars/photo/:filename`, getCarPhotoAsBase64)

// Read one record
router.get(`/cars/:id`, verifyUsersJWTPassword, getCarDocument)

// Add new record
router.post(`/cars`, verifyUsersJWTPassword, checkThatUserIsAnAdministrator, upload.array("carPhotos", parseInt(process.env.MAX_NUMBER_OF_UPLOAD_FILES_ALLOWED)), createNewCarDocument)

// Update one record
router.put(`/cars/:id`, verifyUsersJWTPassword, updateCarDocument)

// Delete one record
router.delete(`/cars/:id`, verifyUsersJWTPassword, checkThatUserIsAnAdministrator, deleteCarDocument)


module.exports = router