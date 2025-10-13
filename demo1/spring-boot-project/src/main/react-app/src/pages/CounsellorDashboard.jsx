import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CounsellorDashboard = () => {
    const [availableTime, setAvailableTime] = useState('');
    const [requests, setRequests] = useState([]);
    const [error, setError] = useState('');

    const studentId = sessionStorage.getItem('studentId');

    useEffect(() => {
        const fetchRequests = async () => {
            if (!studentId) return;
            try {
                const response = await axios.get(`/api/counsellor/${studentId}/requests`);
                setRequests(response.data.data);
            } catch (err) {
                setError('Failed to fetch requests.');
            }
        };

        fetchRequests();
    }, [studentId]);

    const handleAddTimeSlot = async (e) => {
        e.preventDefault();
        setError('');
        if (!availableTime) {
            setError('Please select a time.');
            return;
        }

        try {
            await axios.post(`/api/counsellor/${studentId}/availability`, {
                availableTime: `${availableTime}:00`,
            });
            setAvailableTime('');
            alert('Time slot added successfully!');
        } catch (err) {
            setError(err.response?.data?.message || 'Failed to add time slot.');
        }
    };

    return (
        <div className="flex h-screen">
            <div className="w-1/4 p-4 bg-gray-100 border-r">
                <h3 className="text-lg font-bold mb-4">Add a Time Slot</h3>
                <form onSubmit={handleAddTimeSlot}>
                    <div className="mb-4">
                        <input
                            type="time"
                            value={availableTime}
                            onChange={(e) => setAvailableTime(e.target.value)}
                            className="w-full px-3 py-2 border rounded-md"
                        />
                    </div>
                    <button
                        type="submit"
                        className="w-full px-6 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700"
                    >
                        Add Time
                    </button>
                </form>
            </div>
            <div className="flex-grow p-4">
                <h3 className="text-lg font-bold mb-4">Today's Session Requests</h3>
                {error && <p className="text-red-500 mb-4">{error}</p>}
                <div>
                    {requests.length > 0 ? (
                        requests.map((req) => (
                            <div key={req.finalTime} className="p-4 mb-4 bg-white rounded-lg shadow-md">
                                <p><strong>Student:</strong> {req.studentId}</p>
                                <p><strong>Time:</strong> {new Date(req.finalTime).toLocaleTimeString()}</p>
                            </div>
                        ))
                    ) : (
                        <p>No requests for today.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default CounsellorDashboard;