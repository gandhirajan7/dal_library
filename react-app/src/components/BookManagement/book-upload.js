import React, { useState } from 'react';
import AWS from 'aws-sdk';
import AdminNavBar from "../../components/common/admin-header";

const S3_BUCKET = 'lms-file-upload-bucket-v2';
const REGION = 'us-east-1';

const myBucket = new AWS.S3({
    params: { Bucket: S3_BUCKET },
    region: REGION,
})

const BookUpload = () => {

    const [progress, setProgress] = useState(0);
    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileInput = (e) => {
        setSelectedFile(e.target.files[0]);
    }

    const uploadFile = (file) => {

        const params = {
            ACL: 'public-read-write',
            Body: file,
            Bucket: S3_BUCKET,
            Key: file.name
        };
        myBucket.config.update({
            accessKeyId: '',
            secretAccessKey: '',
            sessionToken: ''
        })

        // console.log(response);
        myBucket.putObject(params)
            .on('httpUploadProgress', (evt) => {
                setProgress(Math.round((evt.loaded / evt.total) * 100))
            })
            .send((err) => {
                if (err) console.log(err)
            })
    }


    return (
        <div>
            <AdminNavBar />
            <br></br>
            <br></br>
            <div>Book Upload In progress {progress}%</div>
            <br></br>
            <input type="file" onChange={handleFileInput} />

            <button onClick={() => uploadFile(selectedFile)}> Upload Books</button>
        </div>
    );
}

export default BookUpload;