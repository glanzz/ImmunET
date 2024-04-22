import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

const AddPet = () => {
  const [formData, setFormData] = useState({
    name: "",
    gender: "",
    species: "",
    dob: "",
    owner: {
      name: "",
      gender: "",
      contact: "",
      address: "",
    },
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.startsWith("owner")) {
      const ownerKey = name.split(".")[1];
      setFormData((prevData) => ({
        ...prevData,
        owner: {
          ...prevData.owner,
          [ownerKey]: value,
        },
      }));
    } else {
      if (name == "dob") {
        console.log(value);
      }
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${process.env.SERVER_API}/doctors/1/pets`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      console.log(response.ok);
      if (response.ok) {
        toast.success("Pet added successfully!");
        setFormData({
          name: "",
          gender: "",
          species: "",
          dob: "",
          owner: {
            name: "",
            gender: "",
            contact: "",
            address: "",
          },
        });
      } else {
        toast.error("Failed to add pet. Please try again later.");
      }
    } catch (error) {
      console.error("Error:", error);
      toast.error("Failed to add pet. Please try again later.");
    }
  };
  console.log("form", formData);
  return (
    <div className="">
      {/* top section design */}
      <ToastContainer />
      <div className="flex sm:gap-6 gap-6 items-center sm:mb-0 mb-4 px-4 pb-5 mt-5">
        <div className="sm:mr-0 mr-4">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour"> ADD PET</h2>
        </div>
      </div>
      {/* top section design */}

      <div className="lg:grid lg:grid-cols-9">
        <div className="col-span-7">
          <div className=" mx-auto  px-12 lg:px-20">
            <form onSubmit={handleSubmit}>
              <div className="bg-primary_colour py-5">
                <h2 className="text-center text-xl text-white font-semibold">ADD PET</h2>
              </div>
              <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col">
                <div className="-mx-3 md:flex mb-2 mt-4">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="name"
                    >
                      Name
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="name"
                      type="text"
                      name="name"
                      value={formData.name}
                      onChange={handleChange}
                      placeholder="Enter Name"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="species"
                    >
                      Species
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="species"
                      type="text"
                      name="species"
                      value={formData.species}
                      onChange={handleChange}
                      placeholder="Enter Species"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="gender"
                    >
                      Gender
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="gender"
                      type="text"
                      name="gender"
                      value={formData.gender}
                      onChange={handleChange}
                      placeholder="Enter Gender"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="dob"
                    >
                      Date of Birth
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="dob"
                      type="date"
                      name="dob"
                      value={formData.dob}
                      onChange={handleChange}
                      placeholder="Enter Date of Birth"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="ownerName"
                    >
                      Owners Name
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="ownerName"
                      type="text"
                      name="owner.name"
                      value={formData.owner.name}
                      onChange={handleChange}
                      placeholder="Enter Owner's Name"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="ownerGender"
                    >
                      Owners Gender
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="ownerGender"
                      type="text"
                      name="owner.gender"
                      value={formData.owner.gender}
                      onChange={handleChange}
                      placeholder="Enter Owner's Gender"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="ownerContact"
                    >
                      Owners Contact
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="ownerContact"
                      type="text"
                      name="owner.contact"
                      value={formData.owner.contact}
                      onChange={handleChange}
                      placeholder="Enter Owner's Contact"
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-2">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="ownerAddress"
                    >
                      Owners Address
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="ownerAddress"
                      type="text"
                      name="owner.address"
                      value={formData.owner.address}
                      onChange={handleChange}
                      placeholder="Enter Owner's Address"
                    />
                  </div>
                </div>
              </div>
              <div className="mt-2">
                <button className="bg-custom_button_color w-full text-black uppercase font-semibold relative h-[50px] border px-3 transition-all before:absolute before:bottom-0 before:left-0 before:top-0 before:w-full before:h-0 before:bg-primary_colour before:transition-all before:duration-700 hover:text-white hover:before:top-0 hover:before:h-full">
                  <span className="relative z-10">Continue</span>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddPet;
