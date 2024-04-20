import PetDetails from "@/components/PetDetails/PetDetails";
import Link from "next/link";
import { useEffect, useState } from "react";
export default function PetList() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [pets, setPets] = useState([]);
  const [selectedPet, setSelectedPet] = useState(null);

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
        const response = await fetch(`${process.env.SERVER_API}/pets`);
        if (!response.ok) {
          throw new Error("Failed to fetch pets");
        }
        const data = await response.json();
        setPets(data);
      } catch (error) {
        console.error("Error fetching pets:", error);
      }
    };

    fetchPets();
  }, []);
  console.log("pets", pets);
  return (
    <div className="p-4 w-full border ">
      <div className="flex sm:gap-6 gap:6 items-center sm:mb-0 mb-4">
        <div className="sm:mr-0 mr-4 ">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour">
            {" "}
            PET LIST
          </h2>
        </div>
      </div>

      {/*   table design */}

      <div className="w-full h-screen ">
        <div className=" sm:px-6 lg:px-8">
          <div className="flex flex-col">
            <div className="-mb-2 py-4 flex flex-wrap flex-grow justify-between">
              <div className="flex items-center py-2">
                <input
                  className="bg-gray-200 appearance-none border-2 border-gray-200 rounded w-full py-2 px-4 text-gray-700 leading-tight focus:outline-none focus:bg-white focus:border-purple-500"
                  id="inline-searcg"
                  type="text"
                  placeholder="Search"
                />
              </div>
              <div className="flex items-center py-2">
                <Link
                  href="/add-pet"
                  className="inline-block px-4 py-2 border border-transparent text-sm leading-5 font-medium  text-white bg-custom_button_color  focus:outline-none focus:shadow-outline"
                >
                  Add Pet
                </Link>
              </div>
            </div>
            <div className="-my-2 py-2 sm:-mx-6 sm:px-6 lg:-mx-8 lg:px-8 overflow-x-auto">
              <div className="align-middle inline-block w-full shadow  sm:rounded-lg border-b border-gray-200">
                <table className="min-w-full">
                  <thead>
                    <tr className=" bg-primary_colour  tracking-wider text-base text-gray-900">
                      <th className="px-6 py-5 text-left" colspan="6">
                        <input
                          className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                          type="checkbox"
                        />
                      </th>

                      <th className="px-6 py-5 text-left">
                        <h2 className="text-center text-white uppercase">
                          PET LIST
                        </h2>
                      </th>
                    </tr>
                    <tr className="bg-indigo-600 bg-opacity-20 border-b border-gray-200 text-xs  text-gray-500 uppercase tracking-wider">
                      <th className="px-6 py-3 text-left font-medium">
                        <input
                          className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                          type="checkbox"
                        />
                      </th>
                      <th className="px-6 py-3 text-left font-medium">Name</th>
                      <th className="px-6 py-3 text-left font-medium">
                        Gender
                      </th>
                      <th className="px-6 py-3 text-left font-medium">
                        species
                      </th>
                      <th className="px-6 py-3 text-left font-medium">DOB</th>
                      <th className="px-6 py-3 text-left font-medium">Owner</th>

                      <th className="px-6 py-3 text-right font-medium">
                        Action
                      </th>
                    </tr>
                  </thead>

                  <tbody className="bg-white">
                    {pets.map((data) => (
                      <tr key={data.id}>
                        <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                          <input
                            className="form-checkbox h-4 w-4 text-indigo-600 transition duration-150 ease-in-out"
                            type="checkbox"
                          />
                        </td>
                        <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                          <div className="text-sm leading-5 text-gray-900">
                            {data.name}
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                          <div className="text-sm leading-5 text-gray-900">
                            {data.gender}
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                          <div className="text-sm leading-5 text-gray-900">
                            {data.species}
                          </div>
                        </td>
                        <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                          <div className="text-sm leading-5 text-gray-900">
                            {data.dob}
                          </div>
                        </td>

                        <td className="px-6 py-4 whitespace-no-wrap border-b border-gray-200">
                          <div className="text-sm leading-5 text-gray-900">
                            {data?.owner?.name}
                          </div>
                        </td>

                        <td className="px-6 py-4 whitespace-no-wrap text-right border-b border-gray-200 text-sm leading-5 font-medium">
                          <button
                            onClick={() => openModal(data)}
                            className="text-indigo-600 hover:text-indigo-900 focus:outline-none focus:underline"
                          >
                            Show
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <PetDetails
        isOpen={isModalOpen}
        onClose={closeModal}
        pet={selectedPet}
      ></PetDetails>
    </div>
  );
}
