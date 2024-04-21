"use client";
import { useRef, useState } from "react";
import Select from "react-select";
import { ToastContainer, toast } from "react-toastify";
// Dynamically import CKEditor

const customStyles = {
  control: (provided, state) => ({
    ...provided,
    background: "#fff",
    borderColor: "#eaecf0",
    height: "60px",
    boxShadow: state.isFocused ? null : null,
    "&:hover": {
      // Overwrites the different states of border
      borderColor: "#eaecf0",
    },
  }),
};

const AddVaccineComponent = () => {
  const [formData, setFormData] = useState({
    name: "",
    frequency: 0,
    cost: 0,
    offset: 0,
    intervals: "",
    species: "",
    mandatory: false,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    let newValue = value;

    if (name === "mandatory") {
      newValue = value === "true";
    } else if (name === "intervals") {
      if (Array.isArray(value)) {
        newValue = value.join(",");
      } else if (typeof value !== "string") {
        newValue = "";
      }
    } else if (value !== "" && !isNaN(value)) {
      newValue = parseFloat(value);
    }

    setFormData((prevData) => ({
      ...prevData,
      [name]: newValue,
    }));
  };

  const handleSelectChange = (selectedOption, meta) => {
    setFormData((prevData) => ({
      ...prevData,
      [meta.name]: selectedOption.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${process.env.SERVER_API}/doctors/1/vaccines`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to create vaccine");
      }

      toast.success("Vaccine created successfully");
      setFormData({
        name: "",
        frequency: 0,
        cost: 0,
        offset: 0,
        intervals: "",
        species: "",
        mandatory: false,
      });
    } catch (error) {
      console.error(error);
      toast.error("Failed to create vaccine");
    }
  };

  const speciesOptions = [
    { value: "CANINE", label: "CANINE" },
    { value: "FELINE", label: "FELINE" },
    { value: "AVIAN", label: "AVIAN" },
  ];

  return (
    <div className="">
      <ToastContainer />
      <div className="flex sm:gap-6 gap:6 items-center sm:mb-0 mb-4 px-4 pb-5 mt-5">
        <div className="sm:mr-0 mr-4 ">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour">
            Add Vaccines
          </h2>
        </div>
      </div>

      <div className="lg:grid lg:grid-cols-9">
        <div className="col-span-7">
          <div className="mx-auto px-12 lg:px-20">
            <form onSubmit={handleSubmit}>
              <div className="bg-primary_colour py-5">
                <h2 className="text-center text-xl text-white font-semibold">ADD VACCINES</h2>
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
                      placeholder="Name Of Vaccine"
                      name="name"
                      value={formData.name}
                      onChange={handleChange}
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="uppercase  text-black text-xs font-bold mb-2"
                      htmlFor="frequency"
                    >
                      Frequency
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="frequency"
                      type="number" // changed type to 'number' for better input control
                      placeholder="Frequency"
                      name="frequency"
                      value={formData.frequency}
                      onChange={handleChange}
                    />
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="cost"
                    >
                      Cost
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="cost"
                      type="number" // changed type to 'number' for better input control
                      placeholder="Cost"
                      name="cost"
                      value={formData.cost}
                      onChange={handleChange}
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="uppercase  text-black text-xs font-bold mb-2"
                      htmlFor="offset"
                    >
                      Offset
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="offset"
                      type="number" // changed type to 'number' for better input control
                      placeholder="Offset"
                      name="offset"
                      value={formData.offset}
                      onChange={handleChange}
                    />
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="intervals"
                    >
                      Intervals
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="intervals"
                      type="text"
                      placeholder="Intervals"
                      name="intervals"
                      value={formData.intervals}
                      onChange={handleChange}
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label
                      className="uppercase  text-black text-xs font-bold mb-2"
                      htmlFor="species"
                    >
                      Species
                    </label>
                    <Select
                      id="species"
                      name="species"
                      options={speciesOptions}
                      className="basic-single"
                      classNamePrefix="select"
                      value={speciesOptions.find((option) => option.value === formData.species)}
                      onChange={handleSelectChange}
                      placeholder="Select Species"
                    />
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="mandatory"
                    >
                      Mandatory
                    </label>
                    <select
                      className="block appearance-none w-full bg-gray-200 border border-gray-200 text-black py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                      id="mandatory"
                      name="mandatory"
                      value={formData.mandatory}
                      onChange={handleChange}
                    >
                      <option value="false">No</option>
                      <option value="true">Yes</option>
                    </select>
                  </div>
                </div>
                <div className="mt-8">
                  <button
                    type="submit"
                    className="bg-custom_button_color w-full text-black uppercase font-semibold relative h-[50px] border px-3 transition-all before:absolute before:bottom-0 before:left-0 before:top-0 before:w-full before:h-0 before:bg-primary_colour before:transition-all before:duration-700 hover:text-white  hover:before:top-0 hover:before:h-full"
                  >
                    <span className="relative z-10">Submit</span>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddVaccineComponent;
