import { useEffect, useState } from "react";
export default function PetList() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [vaccine, setVaccine] = useState([]);

  const [selectedPet, setSelectedPet] = useState(null);
  const [felineList, setFelineList] = useState([]);
  const [avianList, setAvianList] = useState([]);
  const openModal = (pet) => {
    setSelectedPet(pet);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setSelectedPet(null); // Clear the selected pet data
    setIsModalOpen(false);
  };
  useEffect(() => {
    const fetchPets = async () => {
      try {
        const response = await fetch(
          `${process.env.SERVER_API}/doctors/1/vaccines?species=CANINE`
        );
        if (!response.ok) {
          throw new Error("Failed to fetch vaccine");
        }
        const data = await response.json();
        setVaccine(data);
      } catch (error) {
        console.error("Error fetching vaccine:", error);
      }
    };

    fetchPets();
  }, []);
  useEffect(() => {
    const fetchPets = async () => {
      try {
        const response = await fetch(
          `${process.env.SERVER_API}/doctors/1/vaccines?species=FELINE`
        );
        if (!response.ok) {
          throw new Error("Failed to fetch vaccine");
        }
        const data = await response.json();
        setFelineList(data);
      } catch (error) {
        console.error("Error fetching vaccine:", error);
      }
    };

    fetchPets();
  }, []);
  useEffect(() => {
    const fetchPets = async () => {
      try {
        const response = await fetch(
          `${process.env.SERVER_API}/doctors/1/vaccines?species=AVIAN`
        );
        if (!response.ok) {
          throw new Error("Failed to fetch vaccine");
        }
        const data = await response.json();
        setAvianList(data);
      } catch (error) {
        console.error("Error fetching vaccine:", error);
      }
    };

    fetchPets();
  }, []);
  console.log("vaccine:---", avianList);
  return (
    <div className="p-4 w-full border ">
      <div className="flex sm:gap-6 gap:6 items-center sm:mb-0 mb-4">
        <div className="sm:mr-0 mr-4 ">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour">
            {" "}
            VACCINE LIST
          </h2>
        </div>
      </div>

      {/*   table design */}

      <div className="align-middle inline-block w-full shadow sm:rounded-lg border-b border-gray-200">
        <table className="min-w-full">
          {/* Table headers */}
          <thead>
            <tr className="bg-primary_colour tracking-wider text-base text-gray-900">
              <th className="px-6 py-5 text-left" colSpan="6">
                <input
                  className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                  type="checkbox"
                />
              </th>
              <th className="px-6 py-5 text-left">
                <h2 className="text-center text-white uppercase">CANINE</h2>
              </th>
            </tr>
            <tr className="bg-indigo-600 bg-opacity-20 border-b border-gray-200 text-xs text-gray-500 uppercase tracking-wider">
              <th className="px-6 py-3 text-left font-medium">
                <input
                  className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                  type="checkbox"
                />
              </th>
              <th className="px-6 py-3 text-left font-medium">ID</th>
              <th className="px-6 py-3 text-left font-medium">Name</th>
              <th className="px-6 py-3 text-left font-medium">Offset</th>
              <th className="px-6 py-3 text-left font-medium">frequency</th>
              <th className="px-6 py-3 text-left font-medium">species</th>
              <th className="px-6 py-3 text-left font-medium">Mandatory</th>
            </tr>
          </thead>
          <tbody className="bg-white">
            {/* Table rows */}
            {vaccine.map((species) => (
              <tr key={species.id}>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <input
                    className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                    type="checkbox"
                  />
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.id}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.name}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.offset}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.frequency}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.species}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.is_default === true ? <p>True</p> : <p>False</p>}
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="mt-20 align-middle inline-block w-full shadow sm:rounded-lg border-b border-gray-200">
        <table className="min-w-full">
          {/* Table headers */}
          <thead>
            <tr className="bg-primary_colour tracking-wider text-base text-gray-900">
              <th className="px-6 py-5 text-left" colSpan="6">
                <input
                  className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                  type="checkbox"
                />
              </th>
              <th className="px-6 py-5 text-left">
                <h2 className="text-center text-white uppercase">FELINE</h2>
              </th>
            </tr>
            <tr className="bg-indigo-600 bg-opacity-20 border-b border-gray-200 text-xs text-gray-500 uppercase tracking-wider">
              <th className="px-6 py-3 text-left font-medium">
                <input
                  className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                  type="checkbox"
                />
              </th>
              <th className="px-6 py-3 text-left font-medium">ID</th>
              <th className="px-6 py-3 text-left font-medium">Name</th>
              <th className="px-6 py-3 text-left font-medium">Offset</th>
              <th className="px-6 py-3 text-left font-medium">frequency</th>
              <th className="px-6 py-3 text-left font-medium">species</th>
              <th className="px-6 py-3 text-left font-medium">Mandatory</th>
            </tr>
          </thead>
          <tbody className="bg-white">
            {/* Table rows */}
            {felineList.map((species) => (
              <tr key={species.id}>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <input
                    className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                    type="checkbox"
                  />
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.id}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.name}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.offset}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.frequency}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.species}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.is_default === true ? <p>True</p> : <p>False</p>}
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="mt-20 align-middle inline-block w-full shadow sm:rounded-lg border-b border-gray-200">
        <table className="min-w-full">
          {/* Table headers */}
          <thead>
            <tr className="bg-primary_colour tracking-wider text-base text-gray-900">
              <th className="px-6 py-5 text-left" colSpan="6">
                <input
                  className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                  type="checkbox"
                />
              </th>
              <th className="px-6 py-5 text-left">
                <h2 className="text-center text-white uppercase">avian</h2>
              </th>
            </tr>
            <tr className="bg-indigo-600 bg-opacity-20 border-b border-gray-200 text-xs text-gray-500 uppercase tracking-wider">
              <th className="px-6 py-3 text-left font-medium">
                <input
                  className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                  type="checkbox"
                />
              </th>
              <th className="px-6 py-3 text-left font-medium">ID</th>
              <th className="px-6 py-3 text-left font-medium">Name</th>
              <th className="px-6 py-3 text-left font-medium">Offset</th>
              <th className="px-6 py-3 text-left font-medium">frequency</th>
              <th className="px-6 py-3 text-left font-medium">species</th>
              <th className="px-6 py-3 text-left font-medium">Mandatory</th>
            </tr>
          </thead>
          <tbody className="bg-white">
            {/* Table rows */}
            {avianList.map((species) => (
              <tr key={species.id}>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <input
                    className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                    type="checkbox"
                  />
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.id}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.name}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.offset}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.frequency}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.species}
                  </div>
                </td>
                <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                  <div className="text-sm leading-5 text-gray-900">
                    {species.is_default === true ? <p>True</p> : <p>False</p>}
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
