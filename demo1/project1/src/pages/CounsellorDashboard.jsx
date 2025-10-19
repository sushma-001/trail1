import React, { useState, useEffect } from 'react';

const CounsellorDashboard = () => {
    const [availableTime, setAvailableTime] = useState('');
    const [requests, setRequests] = useState([]);
    const [error, setError] = useState('');

    const fetchRequests = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/counsellor/requests', {
                credentials: 'include',
            });
            const data = await response.json();
            if (response.ok) {
                setRequests(data.data);
            } else {
                setError(data.message || 'Failed to fetch requests');
            }
        } catch (err) {
            setError('Failed to connect to the server.');
        }
    };

    useEffect(() => {
        fetchRequests();
    }, []);

    const handleAddTime = async (e) => {
        e.preventDefault();
        setError('');
        try {
            const response = await fetch('http://localhost:8080/api/counsellor/availability', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                credentials: 'include',
                body: JSON.stringify({ availableTime: `${availableTime}:00` }),
            });
            const data = await response.json();
            if (response.ok) {
                alert(data.message);
                setAvailableTime('');
            } else {
                setError(data.message || 'Failed to add time');
            }
        } catch (err) {
            setError('Failed to connect to the server.');
        }
    };

    return (
        <div className="flex">
            <div className="w-1/4 p-4 border-r">
                <h2 className="text-xl font-bold mb-4">Add Availability</h2>
                <form onSubmit={handleAddTime}>
                    <div className="mb-4">
                        <label htmlFor="availableTime" className="block text-sm font-medium text-gray-700">
                            Time
                        </label>
                        <input
                            type="time"
                            id="availableTime"
                            value={availableTime}
                            onChange={(e) => setAvailableTime(e.target.value)}
                            className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                        />
                    </div>
                    <button
                        type="submit"
                        className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700"
                    >
                        Add Time
                    </button>
                </form>
            </div>
            <div className="w-3/4 p-4">
                <h1 className="text-2xl font-bold mb-4">Today's Session Requests</h1>
                {error && <p className="text-red-500">{error}</p>}
                <ul>
                    {requests.map((req) => (
                        <li key={req.session_id} className="border-b p-2">
                            <p><strong>Student:</strong> {req.student_id}</p>
                            <p><strong>Time:</strong> {new Date(req.final_time).toLocaleTimeString()}</p>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default CounsellorDashboard;