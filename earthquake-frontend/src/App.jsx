import { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import Spinner from "react-bootstrap/Spinner";

function App() {
  const [earthquakes, setEarthquakes] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchData = async () => {
    try {
      setLoading(true);

      const res = await fetch(
        "http://localhost:8080/api/earthquakes"
      );

      const data = await res.json();
      setEarthquakes(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="container mt-4">
      <h1 className="text-center mb-4">
        🌍 Earthquake Monitor
      </h1>

      <div className="text-center mb-3">
        <Button onClick={fetchData}>
          Refresh Data
        </Button>
      </div>

      {loading ? (
        <div className="text-center">
          <Spinner animation="border" />
        </div>
      ) : (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Magnitude</th>
              <th>Place</th>
              <th>Time</th>
            </tr>
          </thead>

          <tbody>
            {earthquakes.length === 0 ? (
              <tr>
                <td colSpan="3" className="text-center">
                  No data available. Click Refresh.
                </td>
              </tr>
            ) : (
              earthquakes.map((eq) => (
                <tr key={eq.id}>
                  <td>{eq.magnitude}</td>
                  <td>{eq.place}</td>
                  <td>
                    {new Date(eq.time).toLocaleString()}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </Table>
      )}
    </div>
  );
}

export default App;